package com.finance.tracker.service;

import org.springframework.stereotype.Service;

import com.finance.tracker.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository catRepo;

    public CategoryService(CategoryRepository catRepo) {
        this.catRepo = catRepo;
    }

    public CategoryEntity createCategory(CategoryEntity category) {
        return catRepo.save(category);
    }

}
