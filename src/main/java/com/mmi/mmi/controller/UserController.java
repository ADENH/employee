package com.mmi.mmi.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmi.mmi.dto.UserDto;
import com.mmi.mmi.model.User;
import com.mmi.mmi.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/me")
	public Principal me(Principal principal)
	{
		return principal;
	}
	
	@GetMapping("/me/info")
	public User user(Principal principal) {
		return userService.findByUsername(principal.getName());
	}
	
	@GetMapping("/me/info/test")
	public User usertest() {
		return userService.getCurrentUser();
	}
	
	@PostMapping("/")
	public User saveUser(@RequestBody UserDto user) {
		return userService.add(user);
	}
}
