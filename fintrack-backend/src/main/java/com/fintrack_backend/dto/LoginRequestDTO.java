package com.fintrack_backend.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LoginRequestDTO {
    @Email(message="Valid email is required")
    @NotBlank
    private String email;
    @NotBlank
    private String password;

}
