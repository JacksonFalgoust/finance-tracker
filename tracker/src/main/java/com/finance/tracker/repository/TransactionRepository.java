package com.finance.tracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.TransactionEntity;

/*
 * Repository interface for TransactionRepository.
 * Provides CRUD operations and database interaction methods.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * No additional methods are defined here, but custom queries can be added if needed.
 * The JpaRepository interface provides methods like save, findById, findAll, deleteById, etc.
 */

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByCategoryId(Long categoryId);

}
