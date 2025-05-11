package com.fintrack_backend.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="expenses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long expenseId;

    @Column(nullable = false)
    private String title;

    @Column(name="amount",nullable = false)
    private BigDecimal amount;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="category_id",nullable=false)
    private Category category;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private User user;

}
