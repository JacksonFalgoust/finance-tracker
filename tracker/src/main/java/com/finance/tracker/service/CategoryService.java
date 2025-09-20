package com.finance.tracker.service;

import org.springframework.stereotype.Service;

import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository catRepo;

    public CategoryService(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    public CategoryEntity createCategory(CategoryEntity category) {
        if (category.getName() == null) {
            throw new IllegalArgumentException("Category name cannot be null");
        }

        if (category.getType() == null) {
            category.setType(CategoryEntity.CategoryType.EXPENSE);
        }
        
        return catRepo.save(category);
    }

    public void resetSpentThisMonth(CategoryEntity category) {
        category.setSpentThisMonth(0.0);
    }

}
