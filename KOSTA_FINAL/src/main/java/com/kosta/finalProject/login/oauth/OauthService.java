package com.kosta.finalProject.login.oauth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.kosta.finalProject.models.UserVO;
import com.kosta.finalProject.persistences.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final UserRepository userRepository;
	private final HttpSession httpSession; // 이거 그냥 세션이요
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{
		
		OAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest); // service를 통해 가져온 user의 attribute
		
		// OAuth2 로그인 진행 시 키가 되는 필드 값
		String userNameAttributeName = userRequest.getClientRegistration()
				.getProviderDetails()
				.getUserInfoEndpoint()
				.getUserNameAttributeName();
		
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		// 소셜 정보 <- application=oauth.properties에 등록된 클라이언트만 인식
		
		// DTO(of 메소드를 통해 각 소셜의 로그인 경로로 연결)
		OauthAttributes attributes = OauthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
														// 소셜 정보, 요청 정보의 키, 요청 정보의 회원정보(이름, 이메일)
	
		UserVO user = saveOrUpdate(attributes); // DB에 저장
		httpSession.setAttribute("user", new SessionUser(user)); // 로그인 성공 시 세션에 저장하도록 함
		
		// 리턴값
		return new DefaultOAuth2User(
				Collections.singleton(new SimpleGrantedAuthority(user.getUserRoleEnumTypeKey())), 
				attributes.getAttribute(), 
				attributes.getNameAttributekey());
	}
	
	// 새로운 유저는 저장 || 기존 유저는 업데이트 (findByEmail 씀)
	private UserVO saveOrUpdate (OauthAttributes attributes) {
		UserVO user = userRepository.findByUserEmail(attributes.getEmail()) // 회원의 이메일 찾기
				.map(entity -> entity.update(attributes.getName())) // 동일한 이메일이 있으면 회원명이 업데이트됨
				.orElse(attributes.toEntity()); // 동일한 이메일이 없으면 엔티티에 저장 (이어서 아래 클래스에서 설명)
		
		return userRepository.save(user); // DB에 저장
	}
}
