package com.prodapt.registration.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prodapt.registration.model.User;
import com.prodapt.registration.repository.UserRepository;
import com.prodapt.registration.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findOne(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);
	}

	@Override
	public Boolean verifyUserByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

}
