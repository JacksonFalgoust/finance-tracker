package com.finance.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.RecurringTransactionEntity;
import com.finance.tracker.repository.RecurringTransactoinRepository;
import com.finance.tracker.service.RecurringTransactionService;

@RestController
@RequestMapping("/recurring")
public class RecurringTransactionController {

    private final RecurringTransactoinRepository recRepo;
    private final RecurringTransactionService recService;

    public RecurringTransactionController(RecurringTransactoinRepository recRepo, RecurringTransactionService recService) {
        this.recRepo = recRepo;
        this.recService = recService;
    }

    @PostMapping
    public RecurringTransactionEntity createRecurringTransaction(@RequestBody RecurringTransactionEntity recTransaction) {
        return recService.createRecurringTransaction(recTransaction);
    }

    @GetMapping
    public List<RecurringTransactionEntity> getAllRecurringTransactions() {
        return recRepo.findAll();
    }

    @PostMapping("/processNow")
    public String processNow() {
        recService.processRecurringTransaction();
        return "Recurring transactions processed.";
    }

}
