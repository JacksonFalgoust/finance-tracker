package com.finance.tracker.service;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.AccountEntity;
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

        account.setBalance(account.getBalance() - transaction.getAmount());

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
