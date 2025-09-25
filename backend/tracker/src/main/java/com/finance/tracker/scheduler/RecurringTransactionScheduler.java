package com.finance.tracker.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.finance.tracker.service.RecurringTransactionService;

@Component
public class RecurringTransactionScheduler {

    private final RecurringTransactionService recService;

    public RecurringTransactionScheduler(RecurringTransactionService recService) {
        this.recService = recService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // Runs daily at midnight
    public void runDaily() {
        recService.processRecurringTransaction();
    }

}
