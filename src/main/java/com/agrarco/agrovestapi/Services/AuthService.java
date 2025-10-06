package com.agrarco.agrovestapi.Services;

import com.agrarco.agrovestapi.DTO.LoginRequest;
import com.agrarco.agrovestapi.DTO.LoginResponse;
import com.agrarco.agrovestapi.ScalesEntity.User;
import com.agrarco.agrovestapi.ScalesRepository.userRepository;
import com.agrarco.agrovestapi.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private userRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        Optional<User> userOpt = Optional.ofNullable(userRepository.findByUsername(request.getUsername()));
        if (userOpt.isPresent()) {
            User user = userOpt.get();

            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername(), user.getRole(),user.getName());
                return new LoginResponse(token, user.getUsername(), user.getRole(),user.getName());
            } else {
                System.out.println("Password does not match");
            }

        } else {
            System.out.println("User not found");
        }
        return null;
    }
}
