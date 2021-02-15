package com.mmi.mmi.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mmi.mmi.model.entity.User;

public class CurrentPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	
	public CurrentPrincipal(User user)
	{
		this.user = user;
	}
	
	public User getAccount ()
	{
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> roles = new ArrayList<>();
		if (user.getPosition() != null) {
			roles.add(new SimpleGrantedAuthority(user.getPosition().getName()));
		}
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
