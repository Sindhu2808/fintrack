package com.fintrack_backend.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name="budgets",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "month", "year"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long budgetId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

}

