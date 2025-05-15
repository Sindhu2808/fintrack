package com.fintrack_backend.service;

import com.fintrack_backend.dto.ExpenseFilterRequestDTO;
import com.fintrack_backend.dto.ExpenseMapper;
import com.fintrack_backend.dto.ExpenseRequestDTO;
import com.fintrack_backend.dto.ExpenseResponseDTO;
import com.fintrack_backend.exception.ResourceNotFoundException;
import com.fintrack_backend.model.Category;
import com.fintrack_backend.model.Expense;
import com.fintrack_backend.model.User;
import com.fintrack_backend.repository.CategoryRepository;
import com.fintrack_backend.repository.ExpenseRepository;
import com.fintrack_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public Expense addExpense(ExpenseRequestDTO dto, String email) {
         User user= userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        Category category=categoryRepository.findById(dto.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));
        Expense expense = ExpenseMapper.toEntity(dto, user, category);
        System.out.println("Incoming expense: " + expense.getAmount());
        expenseRepository.save(expense);
        return expense;
    }

    public List<Expense> getAllExpenses() {

        return expenseRepository.findAll();
    }

    public Expense updateExpense(Long id,ExpenseRequestDTO dto, String email) {
        Expense expense=expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense Not Found with id: " + id));
        if(dto.getTitle()!=null) expense.setTitle(dto.getTitle());
        if(dto.getAmount()!=null) expense.setAmount(dto.getAmount());
        if(dto.getDate()!=null) expense.setDate(dto.getDate());
        if(dto.getType()!=null) expense.setType(dto.getType());

        if(dto.getCategoryId()!=null)
        {
           Category category= categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Category Not Found With ID"+dto.getCategoryId()));
           expense.setCategory(category);
        }
        expenseRepository.save(expense);
        return expense;
    }

    public Expense deleteExpense(Long id, String username) {

        Expense expense=expenseRepository.findById(id).orElseThrow(()-> new RuntimeException("Expense doesnt exist with id: " + id));
        if (!expense.getUser().getEmail().equals(username)) {
            throw new AccessDeniedException("You are not authorized to delete this expense");
        }
        expenseRepository.delete(expense);
        return expense;
    }

    public Page<ExpenseResponseDTO> expensesByMonth(String email, ExpenseFilterRequestDTO dto) {
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getPageSize(), Sort.by("date").descending());

        Page<Expense> expenses = expenseRepository.findByFilters(
                email,
                dto.getCategoryName(),
                dto.getMonth(),
                dto.getYear(),
                pageable
        );
        return expenses.map(ExpenseMapper::toDto);



    }
}
