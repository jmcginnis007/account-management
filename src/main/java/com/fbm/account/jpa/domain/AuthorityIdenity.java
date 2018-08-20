package com.fbm.account.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AuthorityIdenity implements Serializable {
	
	private static final long serialVersionUID = -1526825684922852325L;

	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "authority")
	private String authority;

	public AuthorityIdenity(Integer userId, String authority) {
		super();
		this.userId = userId;
		this.authority = authority;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@Override
	public String toString() {
		return "AuthorityIdenity [userId=" + userId + ", authority=" + authority + "]";
	}
	
	// For JPA
	public AuthorityIdenity() {}

}
