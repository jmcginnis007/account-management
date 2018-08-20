package com.fbm.account.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fbm.account.jpa.domain.User;

public class UserPrincipal implements UserDetails {
	
	private static final long serialVersionUID = -2806705762031522681L;
	
	private User user;

	public UserPrincipal(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return user.getAuthorities();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
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
		return user.getEnabled();
	}
	
	public Integer getUserId() {
		return user.getUserId();
	}

	@Override
	public String toString() {
		return "UserPrincipal [user=" + user + "]";
	}
}
