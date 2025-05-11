package com.fintrack_backend.dto;

import com.fintrack_backend.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ExpenseUpdateDTO {
    private String title;
    private BigDecimal amount;
    private LocalDate localDate;
    private TransactionType type;
    private Long categoryId;

}
