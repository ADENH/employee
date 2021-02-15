package com.mmi.mmi.service;

import java.util.List;

import com.mmi.mmi.dto.UserDto;
import com.mmi.mmi.model.entity.User;

public interface UserService {
	void save(User user);
    User findByUsername(String username);
    List<User> findAll();
	User add(UserDto user);
	User getCurrentUser();
}
