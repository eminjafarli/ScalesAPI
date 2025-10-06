package com.agrarco.agrovestapi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String username;
    private String name;

    public UserResponseDTO(String username, String name) {
        this.username = username;
        this.name = name;
    }
}