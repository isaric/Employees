package com.isaric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.isaric.forms.UserCreateForm;
import com.isaric.forms.validators.UserCreateFormValidator;
import com.isaric.services.UserService;

@Controller
public class SecurityController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserCreateFormValidator userCreateFormValidator;
	
	@InitBinder("form")
	public void initBinder(WebDataBinder binder){
		binder.addValidators(userCreateFormValidator);
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String register(Model model){
		UserCreateForm form = new UserCreateForm();
		model.addAttribute("form", form);
		return "register";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@ModelAttribute UserCreateForm form, BindingResult result, Model model){
		if(result.hasErrors()){
			return "register";
			
		}
		try{
			userService.create(form);
		}catch (DataIntegrityViolationException e){
			model.addAttribute("emailExists", "This email address is already in use");
		}
		model.addAttribute("userCreated", "A new user was created");
		return "login";
	}
}
