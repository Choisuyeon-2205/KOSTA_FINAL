package com.kosta.finalProject.login;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.kosta.finalProject.models.BusinessVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SecurityBusiness extends User{
	
	private static final long serialVersionUID = 1L;
	private static final String ROLE_PREFIX="ROLE_";	
	private BusinessVO business;	
	public SecurityBusiness(String businessId, String businessPassword, Collection<? extends GrantedAuthority> authorities) {
		super(businessId, businessPassword, authorities);
	}
	
	public SecurityBusiness(BusinessVO business) {	
		super(business.getBusinessId(), business.getBusinessPassword(), makeRole(business) );
	this.business=business;
	}
	//Role을 여러개 가질수 있도록 되어있음 
	private static List<GrantedAuthority> makeRole(BusinessVO business) {
		List<GrantedAuthority> roleList = new ArrayList<>();
		roleList.add(new SimpleGrantedAuthority(ROLE_PREFIX +business.getBrole()));
		return roleList;
	}


	
	
}
