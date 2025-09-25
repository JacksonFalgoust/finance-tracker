package com.finance.tracker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.RecurringTransactionEntity;

public interface RecurringTransactoinRepository extends JpaRepository<RecurringTransactionEntity, Long> {
    List<RecurringTransactionEntity> findByActiveTrueAndNextOccurrenceBeforeOrNextOccurrenceEquals(LocalDate date1, LocalDate date2);
}
