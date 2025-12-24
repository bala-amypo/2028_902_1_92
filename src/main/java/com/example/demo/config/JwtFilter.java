
package com.example.demo.config;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFilter extends OncePerRequestFilter {

private final JwtUtil jwtUtil;

public JwtFilter(JwtUtil jwtUtil) {
this.jwtUtil = jwtUtil;
}

@Override
protected void doFilterInternal(HttpServletRequest request,
HttpServletResponse response,
FilterChain filterChain)
throws ServletException, IOException {

String path = request.getRequestURI();
if (path.startsWith("/auth/")) {
filterChain.doFilter(request, response);
return;
}

String header = request.getHeader("Authorization");
String token = null;

if (header != null && header.startsWith("Bearer ")) {
token = header.substring(7);
}

if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
Claims claims = jwtUtil.parseToken(token);
String email = claims.get("email", String.class);
String role = claims.get("role", String.class);

// build a simple Spring Security User with role
var authorities = Collections.singleton(
new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_" + role)
);
User principal = new User(email, "", authorities);

UsernamePasswordAuthenticationToken auth =
new UsernamePasswordAuthenticationToken(principal, null, authorities);
auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
SecurityContextHolder.getContext().setAuthentication(auth);
}

filterChain.doFilter(request, response);
}
}
