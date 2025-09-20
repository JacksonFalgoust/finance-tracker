package com.finance.tracker.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing a financial transaction.
 * Each transaction is linked to one account and optionally one category.
 * Examples of transaction types: Income, Expense, Transfer.
 */

@Getter
@Setter
@Entity
public class TransactionEntity {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private Long id;

    @ManyToOne // Many transactions can belong to one category
    @JoinColumn(name = "category_id") // Foreign key column
    private CategoryEntity category; // Category of the transaction linked to CategoryEntity

    @ManyToOne // Many transactions can belong to one account
    @JoinColumn(name = "account_id")  // Foreign key column
    private AccountEntity account; // Account of the transaction linked to AccountEntity

    private LocalDate date; // Date of the transaction, default to current date
    private Double amount;

    public TransactionEntity() {} // Default constructor for JPA
    
    public TransactionEntity(CategoryEntity category, AccountEntity account, LocalDate date, Double amount) {
        this.category = category;
        this.account = account;
        this.date = date;
        this.amount = amount;
    }
}
