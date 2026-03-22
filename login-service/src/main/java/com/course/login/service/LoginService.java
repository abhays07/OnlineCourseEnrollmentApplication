package com.course.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.course.login.dto.UserDTO;
import com.course.login.feign.UserFeignClient;
import com.course.login.util.JwtUtil;

@Service
public class LoginService {

    @Autowired
    private UserFeignClient feignClient;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String login(UserDTO request) {

        UserDTO user = feignClient.getUserByEmail(request.getEmail());

        if(user == null) {
            throw new RuntimeException("User not found");
        }

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}