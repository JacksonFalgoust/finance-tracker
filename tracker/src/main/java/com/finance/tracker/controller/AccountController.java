package com.finance.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final TransactionRepository transRepo;
    private final CategoryRepository catRepo;
    private final AccountRepository accRepo;

    public AccountController(TransactionRepository transRepo, CategoryRepository catRepo, AccountRepository accRepo) {
        this.transRepo = transRepo;
        this.catRepo = catRepo;
        this.accRepo = accRepo;
    }

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return accRepo.findAll();
    }

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account) {
        return accRepo.save(account);
    }

}
