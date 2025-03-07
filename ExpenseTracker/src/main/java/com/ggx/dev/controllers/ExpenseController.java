package com.ggx.dev.controllers;

import com.ggx.dev.models.Expense;
import com.ggx.dev.services.ExpenseService;
import com.ggx.dev.utils.ExpenseDataLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ExpenseController {

    @GetMapping("expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories(){
        List<String> categories = ExpenseService.getAllCategories();

        if(categories.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("expenses")
    public ResponseEntity<Expense> postNewExpense(@RequestBody Expense expense){
        return ResponseEntity.ok(ExpenseService.addExpense(expense));

    }

    @PutMapping("expenses")
    public ResponseEntity<Expense> editExpense(@RequestBody Expense updatedExpense){
        boolean isUpdated = ExpenseService.updateExpense(updatedExpense);
        if(isUpdated){
            System.out.println("--------------------------------");
            ExpenseDataLoader.getExpenseList().forEach(System.out::println);
            return ResponseEntity.ok(updatedExpense);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
