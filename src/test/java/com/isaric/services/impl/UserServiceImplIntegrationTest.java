package com.isaric.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.isaric.EmployeesApplication;
import com.isaric.forms.UserCreateForm;
import com.isaric.model.User;
import com.isaric.testdata.TestDataStubber;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = EmployeesApplication.class)
@TestPropertySource(locations="classpath:test.properties")
@WebIntegrationTest
public class UserServiceImplIntegrationTest {
	
	@Autowired
	private UserServiceImpl userService;
	
	private static List<UserCreateForm> sampleUserForms = TestDataStubber.getSampleUserForms();
	
	@Before
	public void init(){
		for (UserCreateForm form : sampleUserForms){
			userService.create(form);
		}
	}
	
	@After
	public void teardown(){
		for (UserCreateForm form : sampleUserForms){
			userService.deleteUser(form.getEmail());
		}
	}
	
	@Test
	public void addUserSuccessfulTest(){
		UserCreateForm form = new UserCreateForm();
		String email = "test@test.com";
		form.setEmail(email);
		String password = "testpassword";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		form.setPassword(password);
		userService.create(form);
		Optional<User> optional = userService.getUserByEmail(email);
		assertTrue(optional.isPresent());
		User newUser = optional.get();
		assertEquals(email, newUser.getEmail());
		assertTrue(encoder.matches(password, newUser.getPasswordHash()));
		userService.deleteUser(newUser);
	}
	@Test
	public void getAllUserSuccessfullTest(){
		Collection<User> users = userService.getAllUsers();
		assertEquals(3,users.size());
		users = users.stream().filter((User u) -> {
			boolean contains = false;
			for (UserCreateForm form : sampleUserForms){
				if (form.getEmail().equals(u.getEmail())) contains = true;
			}
			return contains;
		}).collect(Collectors.toList());
		assertEquals(3,users.size());
	}
}
