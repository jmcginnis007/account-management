package com.fbm.account.model;

import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewUser {
	
	@NotNull
	@Size(min=8, max=50, message="Username should have at least 8 characters but no more than 50")
	private String username;
	
	@NotNull
	@Size(min=8, max=50, message="Password should have at least 8 characters but no more than 50")
	private String password;
	
	@NotNull
	@Size(min = 1, message="There must be at least 1 authority")
	private Set<Authorities> authorities;
	
	public NewUser() {}
	
	public NewUser(String username, String password, Set<Authorities> authorities) {
		super();
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Authorities> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String toString() {
		return "NewUser [username=" + username + ", password=" + password + ", authorities=" + authorities + "]";
	}
}
