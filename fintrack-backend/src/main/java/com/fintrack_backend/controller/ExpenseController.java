package com.fintrack_backend.controller;

import com.fintrack_backend.dto.*;
import com.fintrack_backend.model.Expense;
import com.fintrack_backend.service.ExpenseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/filterByMonth")
    public ResponseEntity<?> expensesByMonth(@RequestBody @Valid ExpenseFilterRequestDTO dto,
                                             @AuthenticationPrincipal UserDetails userDetails)
    {
        String email=userDetails.getUsername();
        Page<ExpenseResponseDTO> results=expenseService.expensesByMonth(email,dto);
        if(results.isEmpty())
        {
            return ResponseEntity.ok(Map.of(
                    "message","You havent logged any expenses during this period.",
                    "data",List.of()
        ));
        }
        Map<String, Object> response = new HashMap<>();
        response.put("data", results.getContent());
        response.put("currentPage", results.getNumber());
        response.put("totalItems", results.getTotalElements());
        response.put("totalPages", results.getTotalPages());

        return ResponseEntity.ok(response);
    }





}



