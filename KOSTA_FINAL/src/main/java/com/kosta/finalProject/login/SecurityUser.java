package com.kosta.finalProject.login;

import java.util.ArrayList; 
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kosta.finalProject.models.UserVO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityUser extends User{

	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";
    private UserVO user;   
	public SecurityUser(String userId, String userPw, Collection<? extends GrantedAuthority> authorities) {
		super(userId, userPw, authorities);
	}
	public SecurityUser(UserVO user) {	
		super(user.getUserId(), user.getUserPw(), makeRole(user) );
	this.user=user;
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(UserVO user) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getUrole()));
		return roleList;
	}

}
