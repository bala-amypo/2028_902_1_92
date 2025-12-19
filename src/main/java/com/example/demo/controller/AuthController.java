package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final AuthService authService;

public AuthController(AuthService authService) { // constructor injection
this.authService = authService;
}

@PostMapping("/register")
public ResponseEntity<User> register(@RequestBody User user) {
User saved = authService.register(user);
return ResponseEntity.ok(saved);
}

@PostMapping("/login")
public ResponseEntity<String> login(@RequestBody User user) {
String token = authService.login(user.getEmail(), user.getPassword());
return ResponseEntity.ok(token);
}
}