package com.course.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.course.login.dto.UserDTO;
import com.course.login.service.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private LoginService service;

    @PostMapping("/login")
    public String login(@RequestBody UserDTO request) {
        return service.login(request);
    }
}