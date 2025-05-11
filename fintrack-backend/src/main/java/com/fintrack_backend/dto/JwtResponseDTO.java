package com.fintrack_backend.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class JwtResponseDTO {
    private String token;
    private String username;
    private String email;
}
