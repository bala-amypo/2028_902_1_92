
package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework/security.crypto

@Service
public class UserServiceImpl implements UserService {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

@Autowired
public UserServiceImpl(UserRepository userRepository,
PasswordEncoder passwordEncoder) {
this.userRepository = userRepository;
this.passwordEncoder = passwordEncoder;
}

public UserServiceImpl(UserRepository userRepository){
    this.userRepository=userRepository;
    this.passwordEncoder=new BCryptPasswordEncoder();
    }

@Override
public User register(User user) {
// duplicate email
if (userRepository.existsByEmail(user.getEmail())) {
throw new IllegalArgumentException("Email already exists");
}

// default role handled in entity, but ensure not null
if (user.getRole() == null || user.getRole().isBlank()) {
user.setRole("ANALYST");
}

// hash password
String hashed = passwordEncoder.encode(user.getPassword());
user.setPassword(hashed);

return userRepository.save(user);
}

@Override
public User findByEmail(String email) {
return userRepository.findByEmail(email)
.orElseThrow(() ->
new IllegalArgumentException("User not found"));
}
}
