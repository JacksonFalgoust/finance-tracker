package com.finance.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.AccountEntity;
import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;
import com.finance.tracker.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final TransactionRepository transRepo;
    private final CategoryRepository catRepo;
    private final AccountRepository accRepo;

    private final AccountService service;

    public AccountController(TransactionRepository transRepo, CategoryRepository catRepo, AccountRepository accRepo, AccountService service) {
        this.transRepo = transRepo;
        this.catRepo = catRepo;
        this.accRepo = accRepo;
        this.service = service;
    }

    @GetMapping
    public List<AccountEntity> getAccounts() {
        return accRepo.findAll();
    }

    @PostMapping
    public AccountEntity createAccount(@RequestBody AccountEntity account) {
        return service.createAccount(account);
    }

    @GetMapping("/{accountId}")
    public List<TransactionEntity> showTransactionByAccount(@PathVariable Long accountId) {
        return transRepo.findByAccountId(accountId);
    }  

}
