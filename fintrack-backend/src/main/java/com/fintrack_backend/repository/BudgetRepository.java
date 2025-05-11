package com.fintrack_backend.repository;

import com.fintrack_backend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget,Long> {
}
