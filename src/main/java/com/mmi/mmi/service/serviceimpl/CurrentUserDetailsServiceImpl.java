package com.mmi.mmi.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mmi.mmi.model.CurrentPrincipal;
import com.mmi.mmi.model.User;
import com.mmi.mmi.service.UserService;
@Service
public class CurrentUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		
		if (user == null)
			throw new UsernameNotFoundException("username "+username+" not found");

		return new CurrentPrincipal(user);
	}

}
