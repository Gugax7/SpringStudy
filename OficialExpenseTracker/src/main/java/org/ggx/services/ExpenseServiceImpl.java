package org.ggx.services;

import org.ggx.models.Expense;
import org.ggx.utils.ExpenseDataLoader;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final AtomicLong idCounter = new AtomicLong();
    @Override
    public List<Expense> getExpenseByDay(String date) {
        return ExpenseDataLoader.getExpenses()
                .stream()
                .filter(expense -> expense.getDate().equalsIgnoreCase(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Expense> getExpenseByCategoryAndMonth(String category, String month) {
        return ExpenseDataLoader.getExpenses()
                .stream()
                .filter(expense -> expense.getCategory().equalsIgnoreCase(category) && expense.getDate().startsWith(month))
                .toList();
    }

    @Override
    public List<String> getAllExpenseCategories() {
        return ExpenseDataLoader.getExpenses().stream()
                .map(Expense::getCategory)
                .distinct()
                .toList();
    }

    @Override
    public Optional<Expense> getExpenseById(Long id) {
        return ExpenseDataLoader.getExpenses()
                .stream()
                .filter(exp -> exp.getId().equals(id))
                .findFirst();
    }

    @Override
    public Expense addExpense(Expense expense) {
        expense.setId(idCounter.incrementAndGet());
        ExpenseDataLoader.getExpenses().add(expense);
        return expense;
    }

    @Override
    public boolean updateExpense(Expense updatedExpense) {
        Optional<Expense> expense = getExpenseById(updatedExpense.getId());
        if(expense.isPresent()) {
            ExpenseDataLoader.getExpenses()
                    .set(updatedExpense.getId().intValue() -1, updatedExpense);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> expense = getExpenseById(id);
        if(expense.isPresent()){
            ExpenseDataLoader.getExpenses().remove(expense.get());
            return true;
        }
        return false;
    }
}
