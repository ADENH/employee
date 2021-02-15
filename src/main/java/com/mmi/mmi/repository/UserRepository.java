package com.mmi.mmi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmi.mmi.model.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUserName(String username);
	
}
