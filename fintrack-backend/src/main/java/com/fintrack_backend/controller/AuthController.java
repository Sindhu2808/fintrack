package com.fintrack_backend.controller;

import com.fintrack_backend.dto.JwtResponseDTO;
import com.fintrack_backend.dto.LoginRequestDTO;
import com.fintrack_backend.dto.UserRequestDTO;
import com.fintrack_backend.security.CustomUserDetailsService;
import com.fintrack_backend.security.JwtUtils;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
            String token = jwtUtils.generateJwt(loginRequest.getEmail());

            return ResponseEntity.ok(new JwtResponseDTO(
                    token,
                    userDetails.getUsername(),
                    loginRequest.getEmail()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password.");
        }
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequestDTO requestDTO) {
        if (userDetailsService.existsByEmail(requestDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Email already in use.");
        }

        userDetailsService.createUser(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully.");
    }


}
