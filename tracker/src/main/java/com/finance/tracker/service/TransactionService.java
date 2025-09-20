package com.finance.tracker.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;

@Service
public class TransactionService {

    private final TransactionRepository tranRepo;
    private final AccountRepository accRepo;
    private final CategoryRepository catRepo;

    public TransactionService(TransactionRepository repo, AccountRepository accRepo, CategoryRepository catRepo) {
        this.tranRepo = repo;
        this.accRepo = accRepo;
        this.catRepo = catRepo;
    }

    public TransactionEntity createTransaction(TransactionEntity transaction) {

        if (transaction.getCategory() == null) {
            CategoryEntity defaultCategory = catRepo.findById(1L)
                .orElseThrow(() -> new RuntimeException("Default category not found")); // Default 'Other' category
            transaction.setCategory(defaultCategory);
        }

        CategoryEntity category = catRepo.findById(transaction.getCategory().getId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        AccountEntity account = accRepo.findById(transaction.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

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
        
        switch (category.getType()) {
            case EXPENSE -> {
                account.setBalance(account.getBalance() - transaction.getAmount());
                category.setSpentThisMonth(category.getSpentThisMonth() + transaction.getAmount());
            }
            
            case INCOME -> account.setBalance(account.getBalance() + transaction.getAmount());
            default -> throw new RuntimeException("Invalid transaction type: " + category.getType());
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
