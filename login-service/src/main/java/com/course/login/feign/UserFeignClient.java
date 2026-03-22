package com.course.login.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.course.login.dto.UserDTO;

@FeignClient(name = "registration-service")
public interface UserFeignClient {
	@GetMapping("/auth/user")
	UserDTO getUserByEmail(@RequestParam String email);
	
}
