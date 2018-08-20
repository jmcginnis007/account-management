package com.fbm.account.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fbm.account.exception.AccountServiceClientException;
import com.fbm.account.exception.AccountServiceException;
import com.fbm.account.exception.UserNotFoundException;
import com.fbm.account.jpa.domain.Authority;
import com.fbm.account.jpa.domain.User;
import com.fbm.account.jpa.repository.AuthorityRepository;
import com.fbm.account.jpa.repository.UserRepository;
import com.fbm.account.model.NewUser;
import com.fbm.account.model.UpdateUser;
import com.fbm.account.model.UserPrincipal;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new UserPrincipal(user);
	}
	
	public User loadUserByUserId(String id) throws UserNotFoundException, AccountServiceException, AccountServiceClientException {
		User user = null;
		
		if (!validateUUID(id)) throw new AccountServiceClientException("Invalid id '" + (id == null ? "null" : id + "'"));
		
		try {
			user = userRepository.findByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountServiceException(e.getLocalizedMessage());
		}
		
		if (user == null) {
            throw new UserNotFoundException("UserId '" + id + "' does not exist.");
        }
		
        return user;
	}
	
	@Transactional
	public User createNewAccount(NewUser newUser) throws AccountServiceClientException, AccountServiceException {
		User user = null;
		
		try {
			//check for existing user with that username
			user = userRepository.findByUsername(newUser.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountServiceException(e.getLocalizedMessage());
		}
			
		if (user != null) throw new AccountServiceClientException("Username '" + newUser.getUsername() + "' already exists.");
			
		// doesn't exist, so we can create it
		try {
			User newAccount = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()));
			userRepository.save(newAccount); // generates user_id
			
			newUser.getAuthorities().forEach(authority -> {
				newAccount.addAuthority(new Authority(newAccount, authority));
			});
				
			userRepository.save(newAccount); // save authorities
			user = newAccount;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccountServiceException(e.getLocalizedMessage());
		}
		
		return user;
	}
	
	@Transactional
	public User updateAccount(UpdateUser updatedUser, String id) throws AccountServiceClientException, AccountServiceException, UserNotFoundException {
		User existingUser = null;
		
		// need at least one value to update
		if ((StringUtils.isBlank(updatedUser.getPassword()) && updatedUser.getEnabled() == null && updatedUser.getNewDocumentEmailEnabled() == null))
			throw new AccountServiceClientException("No fields found for update");
		
		if (!validateUUID(id)) throw new AccountServiceClientException("Invalid id '" + id + "'");
		
		try {
			existingUser = userRepository.findByUserId(id);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new AccountServiceException(e.getLocalizedMessage());
		}
		
		if (existingUser == null) throw new UserNotFoundException("UserId '" + id + "' does not exist.");
			
		if (StringUtils.isNotBlank(updatedUser.getPassword())) existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
		if (updatedUser.getEnabled() != null) existingUser.setEnabled(updatedUser.getEnabled());
		if (updatedUser.getNewDocumentEmailEnabled() != null) existingUser.setNewDocumentEmailEnabled(updatedUser.getNewDocumentEmailEnabled());
			
		try {
			userRepository.save(existingUser);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new AccountServiceException(e.getLocalizedMessage());
		}
		
		return existingUser;
	}
	
	private boolean validateUUID(String id) {
		boolean valid = true;

		try {
			UUID.fromString(id);
		}
		catch (Exception e) {
			valid = false;
		}
		
		return valid;
	}
}
