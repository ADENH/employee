package com.mmi.mmi.service.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmi.mmi.dto.UserDto;
import com.mmi.mmi.model.entity.Position;
import com.mmi.mmi.model.entity.User;
import com.mmi.mmi.repository.PositionRepository;
import com.mmi.mmi.repository.UserRepository;
import com.mmi.mmi.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PositionRepository positionRepository;
//	@Autowired
//	private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public void save(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        userRepository.save(user);
		
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUserName(username).orElse(null);
	}
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	public User add(UserDto user) {
//		String password = passwordEncoder.encode(user.getPassword());
		String password = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(password);
		
		Position position = positionRepository.findByCode(user.getCodeJabatan());
		
		User dataUser = new User();
		dataUser.setEmail(user.getEmail());
		dataUser.setFirstName(user.getFirstName());
		dataUser.setLastName(user.getLastName());
		dataUser.setPassword(user.getPassword());
		dataUser.setUserName(user.getUserName());
		dataUser.setPosition(position);
		
		String id = Optional.of(userRepository.save(dataUser)).map(v -> v.getId()).get();
		return userRepository.findById(id).get();
	}
	
	public User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
//			CurrentPrincipal principal = (CurrentPrincipal) auth.getPrincipal();
//			return principal.getAccount();
			return findByUsername(auth.getName());
		}
		return null;
	}

}
