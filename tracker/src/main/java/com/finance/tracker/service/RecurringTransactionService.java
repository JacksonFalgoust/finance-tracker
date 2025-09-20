package com.finance.tracker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.entity.CategoryEntity.CategoryType;
import com.finance.tracker.entity.RecurringTransactionEntity;
import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.RecurringTransactoinRepository;
import com.finance.tracker.repository.TransactionRepository;

@Service
public class RecurringTransactionService {

    private final RecurringTransactoinRepository recRepo;
    private final TransactionRepository tranRepo;
    private final CategoryRepository catRepo;
    private final AccountRepository accRepo;

    public RecurringTransactionService(RecurringTransactoinRepository recRepo, TransactionRepository tranRepo, AccountRepository accRepo, CategoryRepository catRepo) {
        this.recRepo = recRepo;
        this.tranRepo = tranRepo;
        this.accRepo = accRepo;
        this.catRepo = catRepo;
    }

    public RecurringTransactionEntity createRecurringTransaction(RecurringTransactionEntity recurring) {

        if (recurring.getCategory() == null) {
            CategoryEntity defaultCategory = catRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default category not found")); // Default 'Other' category
            recurring.setCategory(defaultCategory);
        }

        if (recurring.getStartDate() == null) {
            recurring.setStartDate(LocalDate.now());
        }

        if (recurring.getNextOccurrence() == null) {
            recurring.setNextOccurrence(recurring.getStartDate());
        }

        return recRepo.save(recurring);
    }

    @Transactional
    public void processRecurringTransaction() {
        LocalDate today = LocalDate.now();
        List<RecurringTransactionEntity> dueTransactions = recRepo.findByActiveTrueAndNextOccurrenceBeforeOrNextOccurrenceEquals(today, today);

        for (RecurringTransactionEntity recurring : dueTransactions) {
            // Create a new transaction based on the recurring transaction
            TransactionEntity transaction = new TransactionEntity();
            transaction.setAccount(recurring.getAccount());
            transaction.setCategory(recurring.getCategory());
            transaction.setAmount(recurring.getAmount());
            transaction.setDate(today);
            tranRepo.save(transaction);

            // Update the account balance
            AccountEntity account = recurring.getAccount();
            CategoryEntity category = recurring.getCategory();
            if (category.getType() == CategoryType.INCOME) {
                account.setBalance(account.getBalance() + recurring.getAmount());
            } else if (category.getType() == CategoryType.EXPENSE) {
                account.setBalance(account.getBalance() - recurring.getAmount());
            }

            accRepo.save(account);

            switch (recurring.getFrequency()) {
                case DAILY -> recurring.setNextOccurrence(recurring.getNextOccurrence().plusDays(1));
                case WEEKLY -> recurring.setNextOccurrence(recurring.getNextOccurrence().plusWeeks(1));
                case MONTHLY -> recurring.setNextOccurrence(recurring.getNextOccurrence().plusMonths(1));
                case YEARLY -> recurring.setNextOccurrence(recurring.getNextOccurrence().plusYears(1));
            }

            recRepo.save(recurring);
        }
    }


}
