package com.finance.tracker.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

/*
 * Entity class representing a transaction category.
 * Each category can have multiple transactions associated with it.
 */

@Getter
@Setter
@Entity
public class CategoryEntity {

    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment id
    private Long id;

    private String name;

    @JsonIgnore // Prevents infinite recursion during JSON serialization
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL) // One category can have many transactions 
    private List<TransactionEntity> transactions; // List of transactions linked to this category

    public CategoryEntity() {} // Default constructor for JPA
    public CategoryEntity(String name) {
        this.name = name;
    }

}
