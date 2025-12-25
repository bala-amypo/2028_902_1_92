
/* package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

// Handle custom not‑found exceptions (zone/user/etc.)
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex) {
Map<String, String> body = new HashMap<>();
body.put("error", ex.getMessage()); // message must contain "zone" or "not" based on where it is thrown
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
}

// Handle validation/business rule failures (IllegalArgumentException)
@ExceptionHandler(IllegalArgumentException.class)
public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
Map<String, String> body = new HashMap<>();
body.put("error", ex.getMessage()); // messages already include "exists", "latitude", "longitude", etc.
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
}

// Bean validation (@Valid) errors if you add them later
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException ex) {
Map<String, String> errors = new HashMap<>();
ex.getBindingResult().getFieldErrors()
.forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
}

// Catch‑all for any other unhandled runtime exceptions
@ExceptionHandler(RuntimeException.class)
public ResponseEntity<Map<String, String>> handleRuntime(RuntimeException ex) {
Map<String, String> body = new HashMap<>();
body.put("error", ex.getMessage());
return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
}
} */