
package com.example.demo.controller;

import com.example.demo.config.JwtUtil;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthController {

private final UserService userService;
private final JwtUtil jwtUtil;
private final AuthenticationManager authenticationManager;

public AuthController(UserService userService,
JwtUtil jwtUtil,
AuthenticationManager authenticationManager) {
this.userService = userService;
this.jwtUtil = jwtUtil;
this.authenticationManager = authenticationManager;
}

@PostMapping("/register")
@Operation(summary = "Register new user")
public ResponseEntity<User> register(@RequestBody RegisterRequest request) {
User user = new User(
request.getName(),
request.getEmail(),
request.getPassword(),
request.getRole()
);
User saved = userService.register(user);
return ResponseEntity.ok(saved);
}

@PostMapping("/login")
@Operation(summary = "Login and get JWT")
public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
UsernamePasswordAuthenticationToken authToken =
new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
authenticationManager.authenticate(authToken);

User user = userService.findByEmail(request.getEmail());

String token = jwtUtil.generateToken(
user.getId(),
user.getEmail(),
user.getRole()
);

AuthResponse response = new AuthResponse();
response.setToken(token);
response.setUserId(user.getId());
response.setEmail(user.getEmail());
response.setRole(user.getRole());

return ResponseEntity.ok(response);
}
}