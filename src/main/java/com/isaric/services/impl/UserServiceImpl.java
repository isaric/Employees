package com.isaric.services.impl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.isaric.forms.UserCreateForm;
import com.isaric.model.User;
import com.isaric.repositories.UserRepository;
import com.isaric.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Optional<User> getUserById(long id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Collection<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User create(UserCreateForm form) {
		
		User newUser = new User();
		newUser.setEmail(form.getEmail());
		newUser.setRole(form.getRole());
		newUser.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
		return userRepository.save(newUser);
	}
	
	@Override
	public void deleteUser(User user){
		userRepository.delete(user);
	}
	
	@Override
	public void deleteUser(String email){
		userRepository.deleteByEmail(email);
	}

}
