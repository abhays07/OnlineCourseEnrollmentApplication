package com.course.registration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.course.registration.entity.User;
import com.course.registration.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public User register(@Valid @RequestBody User user) {
		User savedUser = service.register(user);
		savedUser.setPassword(null); // avoid sending password back
		return savedUser;
	}
	
	// Needed for Login Service
    @GetMapping("/user")
    public User getUserByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
	
}
