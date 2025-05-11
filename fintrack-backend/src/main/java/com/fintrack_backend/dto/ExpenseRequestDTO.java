package com.fintrack_backend.dto;


import com.fintrack_backend.model.TransactionType;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseRequestDTO {
    @NotBlank
    private String title;
    @NotNull
    private BigDecimal amount;

    private LocalDate date;
    @NotNull
    private TransactionType type;
    @NotNull
    private Long categoryId;

}
