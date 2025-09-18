package com.finance.tracker.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.entity.CategoryEntity.CategoryType;
import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository tranRepo;
    private final AccountRepository accRepo;

    public TransactionService(TransactionRepository repo, AccountRepository accRepo) {
        this.tranRepo = repo;
        this.accRepo = accRepo;
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {
        AccountEntity account = accRepo.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        CategoryEntity category = transaction.getCategory();

        if (transaction.getAmount() == null) {
            throw new RuntimeException("Transaction amount cannot be null");
        } else if (account.getBalance() < transaction.getAmount()) {
            throw new RuntimeException("Insufficient funds in account");
        } else if (transaction.getAmount() <= 0) {
            throw new RuntimeException("Transaction amount must be positive");
        }

        if (transaction.getDate() == null) {
            transaction.setDate(LocalDate.now());
        }

        if (transaction.getCategory() == null) {
            // Handle no category case, e.g., assign to a default category or leave as null
            // For now, we'll leave it as null
            transaction.setCategory(null);
        }
        
        if (category.getType() == CategoryType.EXPENSE) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else if (category.getType() == CategoryType.INCOME) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else {
            throw new RuntimeException("Invalid transaction type");
        }

        accRepo.save(account);
        return tranRepo.save(transaction);
    }

    public void deleteTransaction(Long transactionId) {
        TransactionEntity transaction = tranRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        AccountEntity account = accRepo.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Associated account not found"));

        account.setBalance(account.getBalance() + transaction.getAmount());
        accRepo.save(account);
        tranRepo.delete(transaction);
    }

}
