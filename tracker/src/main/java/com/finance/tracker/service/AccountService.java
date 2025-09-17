package com.finance.tracker.service;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    public AccountEntity createAccount(AccountEntity account) {
        if (account.getBalance() == null) {
            account.setBalance(0.0);
        } else if (account.getBalance() < 0) {
            throw new RuntimeException("Initial balance cannot be negative");
        }

        return repo.save(account);
    }

    public AccountEntity updateBalance(Long accountId, double changeInBalance) {
        AccountEntity account = repo.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + changeInBalance);
        return repo.save(account);
    }

}
