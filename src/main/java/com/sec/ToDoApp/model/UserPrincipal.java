package com.sec.ToDoApp.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
	
	private UserData userData;

	public UserPrincipal(UserData userData) {
		this.userData = userData;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return userData.getPassword();
	}

	@Override
	public String getUsername() {
		return userData.getUsername();
	}

}
