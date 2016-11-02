package com.isaric.services;

import java.util.Collection;
import java.util.Optional;

import com.isaric.forms.UserCreateForm;
import com.isaric.model.User;

public interface UserService {
	
	Optional<User> getUserById(long id);
	
	Optional<User> getUserByEmail(String email);
	
	Collection<User> getAllUsers();
	
	User create(UserCreateForm form);
	
	void deleteUser(User user);
	
	void deleteUser (String email);
}
