package org.ggx.services;

import org.ggx.models.Expense;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    List<Expense> getExpenseByDay(String date);
    List<Expense> getExpenseByCategoryAndMonth(String category, String month);
    List<String> getAllExpenseCategories();
    Optional<Expense> getExpenseById(Long id);
    Expense addExpense(Expense expense);
    boolean updateExpense(Expense updatedExpense);
    boolean deleteExpense(Long id);
}
