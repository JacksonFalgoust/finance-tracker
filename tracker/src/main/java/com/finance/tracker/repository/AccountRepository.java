package com.finance.tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.tracker.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

}
