package kro.kr.gbsw_star.util.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtTokenService {
    private String secret = "thisisthejsonwebtokenserviceforauthentication";
    Key key = Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(Long id) {
        return Jwts
                .builder()
                .claim("id", id)
                .signWith(key)
                .compact();
    }

    public Long getId(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.get("id", Long.class);
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception ignored) {}

        return false;
    }
}
