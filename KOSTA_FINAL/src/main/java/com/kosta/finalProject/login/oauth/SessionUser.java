package com.kosta.finalProject.login.oauth;

import com.kosta.finalProject.models.UserRoleEnumType;
import com.kosta.finalProject.models.UserVO;

import lombok.Getter;

@Getter
public class SessionUser {
	private String name;
	private String email;
	private UserRoleEnumType role;
	
	public SessionUser(UserVO user) {
		this.name = user.getUserName();
		this.email = user.getUserEmail();
		this.role = user.getUrole();
	}
}
