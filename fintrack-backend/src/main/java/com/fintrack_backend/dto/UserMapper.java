package com.fintrack_backend.dto;

import com.fintrack_backend.model.User;

public class UserMapper {
    public static User toEntity(UserRequestDTO dto)
    {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public static UserResponseDTO toDto(User user)
    {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());

        return dto;

    }
}
