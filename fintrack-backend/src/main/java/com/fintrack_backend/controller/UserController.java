package com.fintrack_backend.controller;

import com.fintrack_backend.dto.UserMapper;
import com.fintrack_backend.dto.UserRequestDTO;
import com.fintrack_backend.dto.UserResponseDTO;
import com.fintrack_backend.model.User;
import com.fintrack_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> addUser(@RequestBody @Valid UserRequestDTO request) {
        User user = UserMapper.toEntity(request);
        userService.addUser(user);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(UserMapper::toDto)
                .toList();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User Deleted Successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
