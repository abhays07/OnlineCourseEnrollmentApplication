package com.course.registration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.registration.entity.User;
import com.course.registration.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {

    	 if(user.getPassword() == null || user.getPassword().isBlank()) {
             throw new RuntimeException("Password cannot be null");
         }
    	 
        if(repository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email).orElse(null);
    }
}