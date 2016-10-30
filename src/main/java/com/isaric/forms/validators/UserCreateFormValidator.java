package com.isaric.forms.validators;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.isaric.forms.UserCreateForm;
import com.isaric.services.UserService;


@Component
public class UserCreateFormValidator implements Validator {
	
	@Autowired
	private UserService userService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(UserCreateForm.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserCreateForm form = (UserCreateForm) target;
		validatePasswords(errors,form);
		validateEmail(errors, form);
	}

	private void validateEmail(Errors errors, UserCreateForm form) {
		if(!isValidEmailAddress(form.getEmail())){
			errors.reject("emailInvalid", "This is not a valid email");
		}
		
		if (userService.getUserByEmail(form.getEmail()).isPresent()){
			errors.reject("emailExists", "This email is already in use");
		}
		
	}

	private boolean isValidEmailAddress(String email) {
		boolean result = true;
		try{
			InternetAddress emailAddress = new InternetAddress(email);
		}catch(AddressException e){
			result = false;
		}
		return result;
	}

	private void validatePasswords(Errors errors, UserCreateForm form) {
		if (!form.getPassword().equals(form.getPasswordRepeat())){
			errors.reject("passwordMismatch", "Passwords do not match");
		}
	}
	

}
