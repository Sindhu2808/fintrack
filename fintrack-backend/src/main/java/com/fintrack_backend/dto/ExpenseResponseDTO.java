package com.fintrack_backend.dto;

import com.fintrack_backend.model.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ExpenseResponseDTO {

    private String title;
    private BigDecimal amount;
    private LocalDate date;
    private TransactionType type;
    private Long categoryId;
    private String categoryName;


}
