package com.fbm.account.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import com.fbm.account.jpa.domain.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, String> {

}
