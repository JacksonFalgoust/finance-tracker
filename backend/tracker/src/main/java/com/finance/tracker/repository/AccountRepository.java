package com.finance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.AccountEntity;

/*
 * Repository interface for AccountEntity.
 * Provides CRUD operations and database interaction methods.
 * Extends JpaRepository to leverage Spring Data JPA functionalities.
 * No additional methods are defined here, but custom queries can be added if needed.
 * The JpaRepository interface provides methods like save, findById, findAll, deleteById, etc.
 */

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
