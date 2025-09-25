package com.finance.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;
import com.finance.tracker.service.TransactionService;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository transRepo;
    private final CategoryRepository catRepo;
    private final AccountRepository accRepo;

    private final TransactionService service;

    public TransactionController(TransactionRepository transRepo, CategoryRepository catRepo, AccountRepository accRepo, TransactionService service) {
        this.transRepo = transRepo;
        this.catRepo = catRepo;
        this.accRepo = accRepo;
        this.service = service;
    }

    @GetMapping
    public List<TransactionEntity> getTransactions() {
        return transRepo.findAll();
    }

    @PostMapping
    public TransactionEntity createTransaction(@RequestBody TransactionEntity transaction) {
        return service.createTransaction(transaction);
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long transactionId) {
        service.deleteTransaction(transactionId);
    }
    

}
