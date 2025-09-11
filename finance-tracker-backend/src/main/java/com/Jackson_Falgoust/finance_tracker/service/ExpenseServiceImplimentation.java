package com.Jackson_Falgoust.finance_tracker.service;

import org.springframework.stereotype.Service;

import com.Jackson_Falgoust.finance_tracker.dto.ExpenseDTO;
import com.Jackson_Falgoust.finance_tracker.entity.Expense;
import com.Jackson_Falgoust.finance_tracker.repository.ExpenseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImplimentation implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    private Expense saveOrUpdateExpense(Expense expense, ExpenseDTO expenseDTO) {
        expense.setTitle(expenseDTO.getTitle());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmaount());
        expense.setCategory(expenseDTO.getCategory());
        expense.setDescription(expenseDTO.getDescription());

        return expenseRepository.save(expense);
    }

}
