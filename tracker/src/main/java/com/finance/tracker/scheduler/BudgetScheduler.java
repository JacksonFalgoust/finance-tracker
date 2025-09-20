package com.finance.tracker.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finance.tracker.entity.CategoryEntity;
import com.finance.tracker.repository.CategoryRepository;

@Component
public class BudgetScheduler {

    private final CategoryRepository repo;

    public BudgetScheduler(CategoryRepository repo) {
        this.repo = repo;
    }

    @Scheduled(cron = "0 0 0 1 * *") // Runs at midnight on the 1st of every month
    public void resetBudget() {
        List<CategoryEntity> categories = repo.findAll();

        for (CategoryEntity category : categories) {
            category.setSpentThisMonth(0.0);
        }

        repo.saveAll(categories);
        System.out.println("Budgets reset for new month");
    }

}
