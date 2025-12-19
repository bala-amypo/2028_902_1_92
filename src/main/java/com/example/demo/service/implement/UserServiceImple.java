package com.example.Hotspot.service;

import com.example.Hotspot.model.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);  // must throw exception if not found
}
