package com.agrarco.agrovestapi.Security;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ManualPassword {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    public static boolean matches(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
    public static void main(String[] args) {
        String password = "s";
        String hashed = hashPassword(password);
        System.out.println("BCrypt hashed password: " + hashed);

        System.out.println("Password matches? " + matches(password, hashed));
    }
}
