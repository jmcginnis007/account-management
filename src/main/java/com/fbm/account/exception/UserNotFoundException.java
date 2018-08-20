package com.fbm.account.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{

	private static final long serialVersionUID = 8352041165576465032L;

	public UserNotFoundException(String string) {
		super(string);
	}
}
