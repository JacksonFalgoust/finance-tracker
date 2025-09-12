package com.finance.tracker.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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

    private String name;
    private String type; // e.g., "Checking", "Savings", "Credit Card"
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL) // One account can have many transactions
    private List<TransactionEntity> transactions; // List of transactions linked to this account

    public AccountEntity() {} // Default constructor for JPA
    
    public AccountEntity(String name, String type, Double balance) {
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    public AccountEntity(String name, String type) {
        this.name = name;
        this.type = type;
        this.balance = 0.0; // Default balance
    }

}
