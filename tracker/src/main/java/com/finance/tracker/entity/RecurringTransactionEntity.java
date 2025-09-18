package com.finance.tracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RecurringTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private Long id;

    @ManyToOne // Many transactions can belong to one category
    @JoinColumn(name = "category_id") // Foreign key column
    private CategoryEntity category; // Category of the transaction linked to CategoryEntity

    @ManyToOne // Many transactions can belong to one account
    @JoinColumn(name = "account_id")  // Foreign key column
    private AccountEntity account; // Account of the transaction linked to AccountEntity

    @Enumerated(EnumType.STRING)
    private Frequency frequency; // e.g., "Monthly", "Weekly"

    private Double amount;
    private LocalDate date = LocalDate.now(); // Date of the transaction, default to current date
    private LocalDate nextOccurrence; // e.g., "2024-07-01"
    private boolean active = true; // Is the recurring transaction active?

    public enum Frequency {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    public RecurringTransactionEntity() {} // Default constructor for JPA

}
