package com.agrarco.agrovestapi.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String jwt;
    private String username;
    private String role;
    private String name;

    public LoginResponse(String jwt, String username, String role, String name) {
        this.jwt = jwt;
        this.username = username;
        this.role = role;
        this.name = name;
    }

    public String getJwt() { return jwt; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getName() { return name; }
}
