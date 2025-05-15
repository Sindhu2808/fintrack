package com.fintrack_backend.repository;

import com.fintrack_backend.dto.ExpenseResponseDTO;
import com.fintrack_backend.model.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    @Query("SELECT e FROM Expense e " +
            "WHERE e.user.email = :email " +
            "AND (:categoryName IS NULL OR e.category.categoryName = :categoryName) " +
            "AND EXTRACT(MONTH FROM e.date) = :month " +
            "AND EXTRACT(YEAR FROM e.date) = :year")
    Page<Expense> findByFilters(
            @Param("email") String email,
            @Param("categoryName") String categoryName,
            @Param("month") int month,
            @Param("year") int year,
            Pageable pageable);
}