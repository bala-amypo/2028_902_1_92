
package com.example.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

// Secure 256-bit key for HS256
private static final Key KEY =
Keys.secretKeyFor(SignatureAlgorithm.HS256);

private static final long VALIDITY_MS = 24 * 60 * 60 * 1000;

public String generateToken(Long userId, String email, String role) {

Map<String, Object> claims = new HashMap<>();
claims.put("userId", userId);
claims.put("email", email);
claims.put("role", role);

Date now = new Date();
Date expiry = new Date(now.getTime() + VALIDITY_MS);

return Jwts.builder()
.setClaims(claims)
.setSubject(email)
.setIssuedAt(now)
.setExpiration(expiry)
.signWith(KEY, SignatureAlgorithm.HS256)
.compact();
}

public Claims parseToken(String token) {
return Jwts.parserBuilder()
.setSigningKey(KEY)
.build()
.parseClaimsJws(token)
.getBody();
}
}
