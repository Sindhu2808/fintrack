package com.fintrack_backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private final Key jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtEpirationMs = 86400000;

    public String generateJwt(String email)
    {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtEpirationMs))
                .signWith(jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt(String token)
    {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwt(String token)
    {
        try
        {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        }
        catch(JwtException e)
        {
            return false;
        }
    }
}
