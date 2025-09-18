package com.finance.tracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing a financial account.
 * Each account can have multiple transactions associated with it.
 * Examples of account types: Checking, Savings, Credit Card.
 */

@Getter
@Setter
@Entity
public class AccountEntity {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private Long id;

    @Enumerated(EnumType.STRING) // Store enum as string in DB
    private AccountType type; // e.g., "Checking", "Savings", "Credit Card"

    @JsonIgnore // Prevents infinite recursion during JSON serialization
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) // One account can have many transactions
    private List<TransactionEntity> transactions; // List of transactions linked to this account

    private String name;
    private Double balance;

    public enum AccountType {
        CHECKING,
        SAVINGS,
        CREDIT_CARD,
        CASH,
        INVESTMENT
    }

    public AccountEntity() {} // Default constructor for JPA
    
    public AccountEntity(String name, AccountType type, Double balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
    }
}
