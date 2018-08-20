package com.fbm.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fbm.account.exception.AccountServiceClientException;
import com.fbm.account.exception.AccountServiceException;
import com.fbm.account.exception.UserNotFoundException;
import com.fbm.account.jpa.domain.User;
import com.fbm.account.model.NewUser;
import com.fbm.account.model.UpdateUser;
import com.fbm.account.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	AccountService accountService;

	@PreAuthorize("#oauth2.hasScope('read') and hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getAccount(@PathVariable String id)
			throws AccountServiceClientException, AccountServiceException, UserNotFoundException {
		User user = accountService.loadUserByUserId(id);
		return user;
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_ADMIN')")
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User createAccount(@Valid @RequestBody NewUser request)
			throws AccountServiceClientException, AccountServiceException {
		return accountService.createNewAccount(request);
	}

	@PreAuthorize("#oauth2.hasScope('write') and hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public User updateAccount(@Valid @RequestBody UpdateUser request, @PathVariable String id)
			throws AccountServiceClientException, AccountServiceException, UserNotFoundException {
		return accountService.updateAccount(request, id);
	}

}
