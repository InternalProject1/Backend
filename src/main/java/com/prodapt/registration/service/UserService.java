package com.prodapt.registration.service;

import java.util.List;
import java.util.Optional;

import com.prodapt.registration.model.User;

public interface UserService {

	User save(User user);
	
	List<User> findAll();

	Optional<User> findOne(Long id);

	void delete(long id);
	
	Boolean verifyUserByUsername(String username);
}
