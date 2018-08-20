package com.fbm.account.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountServiceClientException extends Exception {

	private static final long serialVersionUID = -7369854482139722651L;

	public AccountServiceClientException(String string) {
		super(string);
	}

}
