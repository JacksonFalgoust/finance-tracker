package com.finance.tracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.entity.TransactionEntity;
import com.finance.tracker.repository.AccountRepository;
import com.finance.tracker.repository.CategoryRepository;
import com.finance.tracker.repository.TransactionRepository;
import com.finance.tracker.service.CategoryService;


@RestController
@RequestMapping("/categories")
public class CategoryController {


    private final TransactionRepository transRepo;
    private final CategoryRepository catRepo;
    private final AccountRepository accRepo;

    private final CategoryService service;

    public CategoryController(TransactionRepository transRepo, CategoryRepository catRepo, AccountRepository accRepo, CategoryService service) {
        this.transRepo = transRepo;
        this.catRepo = catRepo;
        this.accRepo = accRepo;
        this.service = service;
    }

    @GetMapping
    public List<CategoryEntity> getCategories() {
        return catRepo.findAll();
    }

    @PostMapping
    public CategoryEntity createCategory(@RequestBody CategoryEntity category) {
        return service.createCategory(category);
    }

    @GetMapping("/{categoryId}")
    public List<TransactionEntity> showTransactionsByCategory(@PathVariable Long categoryId) {
        return transRepo.findByCategoryId(categoryId);
    }
    
}
