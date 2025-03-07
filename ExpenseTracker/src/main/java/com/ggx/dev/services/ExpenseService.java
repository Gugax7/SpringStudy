package com.ggx.dev.services;

import com.ggx.dev.models.Expense;
import com.ggx.dev.utils.ExpenseDataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class ExpenseService {
// I could use interface implementation to it too...
// probably using these methods as statics is bad, but let's see where this lead me.
    public static final AtomicLong idCounter = new AtomicLong();
    public static List<String> getAllCategories(){
        return ExpenseDataLoader.getExpenseList()
                .stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    public static Optional<Expense> getById(Long id){
        return ExpenseDataLoader.getExpenseList().stream()
                .filter(expense -> expense.getId().equals(id))
                .findFirst();
    }
    public static Expense addExpense(Expense expense){
        expense.setId(idCounter.incrementAndGet());
        ExpenseDataLoader.getExpenseList().add(expense);
        return expense;
    }
    public static boolean updateExpense(Expense expense){
        Optional<Expense> oldExpense = getById(expense.getId());
        if(oldExpense.isPresent()){
            ExpenseDataLoader.getExpenseList().remove(oldExpense.get());
            ExpenseDataLoader.getExpenseList().add(expense);
            return true;
        }
        return false;
    }
    public static boolean deleteExpense(Expense expense){
        return false;
    }
}
