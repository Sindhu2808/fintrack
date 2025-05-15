package com.fintrack_backend.dto;

import com.fintrack_backend.model.Category;
import com.fintrack_backend.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseResponseDTO {

    private Long expenseId;
    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;
    private String categoryName;

}
