package com.fbm.account.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.fbm.account.jpa.domain.User;

public interface UserRepository extends CrudRepository<User, String> {
	User findByUsername(String username);
	User findByUserId(String id);
}
