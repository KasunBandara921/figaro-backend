package com.figaro.salon_management_system.security;




import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;



@Component
public class JwtUtil {

    // In real projects, keep this secret in application.properties, not hardcoded
    private final String SECRET = "change-this-to-a-very-long-random-secret-string-1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long EXPIRATION = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    public String extractRole(String token) {
        return (String) Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().get("role");
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}