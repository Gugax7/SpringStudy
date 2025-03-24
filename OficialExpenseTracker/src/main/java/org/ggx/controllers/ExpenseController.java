package org.ggx.controllers;

import org.ggx.models.Expense;
import org.ggx.services.ExpenseService;
import org.ggx.utils.ExpenseDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("expenses/categories")
    public ResponseEntity<List<String>> getAllExpenseCategories(){
        List<String> categories = expenseService.getAllExpenseCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("expenses/day/{date}")
    public ResponseEntity<List<Expense>> getExpensesByDay(
            @PathVariable String date){
        List<Expense> expenseList = expenseService.getExpenseByDay(date);
        return ResponseEntity.ok(expenseList);
    }

    @GetMapping("expenses/categories/{category}/month")
    public ResponseEntity<List<Expense>> getExpenseByCategoryAndMonth(
            @PathVariable String category,
            @RequestParam String month
    ){
        return ResponseEntity.ok(expenseService
                .getExpenseByCategoryAndMonth(category,month));
    }
    @GetMapping("expenses")
    public ResponseEntity<Optional<Expense>> getExpenseById(@RequestParam Long id){
        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    @GetMapping("expenses/all")
    public ResponseEntity<List<Expense>> getAllExpenses(){
        return ResponseEntity.ok(ExpenseDataLoader.getExpenses());
    }

    @PostMapping("expenses")
    public ResponseEntity<Expense> addExpense(
            @RequestBody Expense expense
    ){
        Expense newExpense = expenseService.addExpense(expense);
        return new ResponseEntity<>(newExpense, HttpStatus.CREATED);
    }

    @PutMapping("expenses")
    public ResponseEntity<Expense> updateExpense(
            @RequestBody Expense updatedExpense
    ){
        boolean itWorked = expenseService.updateExpense(updatedExpense);
        if(itWorked) return new ResponseEntity<>(updatedExpense, HttpStatus.ACCEPTED);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("expenses/delete")
    public ResponseEntity<String> deleteExpense(
            @RequestParam Long id
        ){
        boolean itWorked = expenseService.deleteExpense(id);
        if(itWorked){
            return new ResponseEntity<>("Expense deleted successfully", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>("Expense not found", HttpStatus.NOT_FOUND);
    }

}
