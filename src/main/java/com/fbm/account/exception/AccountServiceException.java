package com.fbm.account.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AccountServiceException extends Exception {

	private static final long serialVersionUID = -7369854482139722651L;

	public AccountServiceException(String string) {
		super(string);
	}

}
