package com.fintrack_backend.controller;

import com.fintrack_backend.dto.*;
import com.fintrack_backend.model.Expense;
import com.fintrack_backend.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    @PostMapping()
    public ResponseEntity<?> addExpense(@RequestBody ExpenseRequestDTO expenseRequest,@AuthenticationPrincipal UserDetails userDetails) {
        Expense savedExpense=expenseService.addExpense(expenseRequest,userDetails.getUsername());
        System.out.println("DTO received: " + expenseRequest);
        return ResponseEntity.ok().body("Successfully added expense");
    }

    @GetMapping("/allExpenses")
    public List<ExpenseResponseDTO> getAll()
    {
        return expenseService.getAllExpenses()
                .stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id,
                                           @RequestBody ExpenseRequestDTO expenseRequest,
                                           @AuthenticationPrincipal UserDetails userDetails) {
        Expense updatedExpense=expenseService.updateExpense(id,expenseRequest,userDetails.getUsername());
        return ResponseEntity.ok().body("Successfully edited expense");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id,@AuthenticationPrincipal UserDetails userDetails) {
        Expense deleteExpense=expenseService.deleteExpense(id,userDetails.getUsername());
        return ResponseEntity.ok().body("Successfully deleted expense");
    }




}



