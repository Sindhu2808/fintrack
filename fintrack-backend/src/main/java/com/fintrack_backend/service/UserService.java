package com.fintrack_backend.service;

import com.fintrack_backend.model.User;
import com.fintrack_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        log.info("User Created Successfully");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean deleteUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            log.info("User deleted with id {}", userId);
            return true;
        }).orElse(false);
    }
}
