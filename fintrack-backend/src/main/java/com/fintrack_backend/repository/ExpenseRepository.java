package com.fintrack_backend.repository;

import com.fintrack_backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

}
