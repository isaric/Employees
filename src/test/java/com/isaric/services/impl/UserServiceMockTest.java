package com.isaric.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.isaric.forms.UserCreateForm;
import com.isaric.model.User;
import com.isaric.repositories.UserRepository;
import com.isaric.testdata.TestDataStubber;

public class UserServiceMockTest {
	
	private static UserServiceImpl userService;
	private static UserRepository repository;
	private static List<User> allUsers;
	
	@BeforeClass
	public static void setupClass(){
		userService = new UserServiceImpl();
		repository = Mockito.mock(UserRepository.class);
		allUsers = TestDataStubber.getSampleUsers();
		Mockito.when(repository.findAll()).thenReturn(allUsers);
		userService.setUserRepository(repository);
	}
	
	@Test
	public void getAllUsersMockTest(){
		assertEquals(3, userService.getAllUsers().size());
		assertTrue(userService.getAllUsers().containsAll(allUsers));
	}
	
	@Test
	public void createUserMockTest(){
		UserCreateForm form = new UserCreateForm();
		User user = new User();	
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String email = "test@test.com";
		String password = "password";
		user.setEmail(email);
		user.setPasswordHash(encoder.encode(password));
		form.setEmail(email);
		form.setPassword(password);
		Mockito.when(repository.save(user)).thenReturn(user);
		assertEquals(user,userService.create(form));
		assertTrue(encoder.matches(password, userService.create(form).getPasswordHash()));
	}
	
}
