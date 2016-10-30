package com.isaric.model;

import org.springframework.security.core.authority.AuthorityUtils;
import com.isaric.model.User;
import com.isaric.enums.Role;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
	private User user;
	
	public CurrentUser(User user){
		super(user.getEmail(), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
		this.user = user;
	}
	
	public User getUser(){
		return user;
	}
	
	public Long getId(){
		return user.getId();
	}
	
	public Role getRole(){
		return user.getRole();
	}
}
