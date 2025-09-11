package com.Jackson_Falgoust.finance_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Jackson_Falgoust.finance_tracker.entity.Expense;

@Repository

public interface ExpenseRepository extends JpaRepository<Expense, Long>{

    

}
