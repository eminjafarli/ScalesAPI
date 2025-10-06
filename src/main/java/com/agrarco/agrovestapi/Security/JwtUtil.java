package com.agrarco.agrovestapi.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDkPiSh6wT1GyyPoxhTny7Z8DvXi+RbVj+N+UM0Zqg/9nrp0xYwUzQXmM1RfquX1WLpZb6Bq+v1g53vNfJyCkp7vD9OL1s+1vZkpSvyHKRJfJlpguF7Kw+1KX+bzHcXUliJgQScf/rl2zWgiS7q+F8Z5nVgLCw54zFqy75QIDAQAB";
    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    public String generateToken(String username, String role,String name) {
        return Jwts.builder()
                .setSubject(username)
                .setSubject(name)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
