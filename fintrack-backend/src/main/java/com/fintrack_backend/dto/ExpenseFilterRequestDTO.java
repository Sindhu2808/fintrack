package com.fintrack_backend.dto;

import lombok.Data;

@Data
public class ExpenseFilterRequestDTO {
    private Integer month;
    private Integer year;
    private String categoryName;
    private Integer page=0;
    private Integer pageSize=10;
}
