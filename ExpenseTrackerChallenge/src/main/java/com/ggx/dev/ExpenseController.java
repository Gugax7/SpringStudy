package com.ggx.dev;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ExpenseController {


    @Autowired
    private ExpenseDataLoader expenseDataLoader;

    @GetMapping("/expenses/day")
    public List<Expense> getValuesString(@RequestParam(value="date") String date){
        return ExpenseDataLoader.getExpenses().stream()
                .filter(expense -> expense.getDate().equals(date))
                .collect(Collectors.toList());
    }

    @GetMapping("/expenses")
    public String getAllExpenses(){
        return ExpenseDataLoader.getExpenses().toString();
    }

    @GetMapping("expenses/categories")
    public String getAllCategories(){
        String[] array = ExpenseDataLoader.getExpenses().stream()
                .map(Expense::getCategory)
                .toArray(String[]::new);
        return Arrays.toString(array);
    }
}
