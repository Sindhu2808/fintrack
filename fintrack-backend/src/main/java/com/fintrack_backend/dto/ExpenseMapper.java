package com.fintrack_backend.dto;

import com.fintrack_backend.model.*;

import java.time.LocalDate;

public class ExpenseMapper {

    public static Expense toEntity(ExpenseRequestDTO dto, User user, Category category) {
        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate() != null ? dto.getDate() : LocalDate.now());
        expense.setType(dto.getType());
        expense.setCategory(category);
        expense.setUser(user);
        return expense;
    }
    public static ExpenseResponseDTO toDto(Expense expense)
    {
        ExpenseResponseDTO dto=new ExpenseResponseDTO();
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        dto.setType(expense.getType());
        dto.setCategoryName(expense.getCategory().getCategoryName());
        return dto;

    }
}
