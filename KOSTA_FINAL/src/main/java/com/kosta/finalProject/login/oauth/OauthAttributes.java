package com.kosta.finalProject.login.oauth;

import java.util.Map;

import com.kosta.finalProject.models.UserRoleEnumType;
import com.kosta.finalProject.models.UserVO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthAttributes {

	// Field
	private Map<String, Object> attribute; // 회원정보
	private String nameAttributekey; // 요청 정보의 키
	private String name; // 이름
	private String email; // 이메일
	
	
	@Builder
	public OauthAttributes(Map<String, Object> attribute, String nameAttributekey, String name, String email) {
		super();
		this.attribute = attribute;
		this.nameAttributekey = nameAttributekey;
		this.name = name;
		this.email = email;
	}
	
	// SNS 구분 Method (OauthService의 of 메소드와 연결)
	public static OauthAttributes of(String registrationId, String usernameAttributename, Map<String, Object> attribute) {
		
		// 소셜 -> 카카오
		if(registrationId.equals("kakao")) {
			return ofKakao(usernameAttributename, attribute);
		}
		
		// 소셜 -> 네이버
		else if(registrationId.equals("naver")) {
			return ofNaver(usernameAttributename, attribute);
		}
		
		// 나머지 (소셜 -> 구글)
		else {
			return ofGoogle(usernameAttributename, attribute);
		}
		
	}
	
	// 구글에서 정보 가져오는 Method
	private static OauthAttributes ofGoogle(String usernameAttributename, Map<String, Object> attribute) {
		return OauthAttributes.builder()
				.name((String) attribute.get("name")) // 이름을 기져오기 위해서는 구글에서 정한 "name"이라는 문자를 사용해야 한다 (UserVO랑은 다름!)
				.email((String) attribute.get("email"))
				.attribute(attribute)
				.nameAttributekey(usernameAttributename)
				.build();
	}

	
	// 카카오에서 정보 가져오는 Method
	private static OauthAttributes ofKakao(String usernameAttributename, Map<String, Object> attribute) {
		// 요청 정보로 객체 생성
		Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
		
		return OauthAttributes.builder()
				.name((String) profile.get("nickname")) 
				.email((String) kakaoAccount.get("email"))
				.attribute(attribute)
				.nameAttributekey(usernameAttributename)
				.build();
	}
	
	
	// 네이버에서 정보 가져오는 Method
	private static OauthAttributes ofNaver(String usernameAttributename, Map<String, Object> attribute) {
			// 요청 정보로 객체 생성
			Map<String, Object> response = (Map<String, Object>) attribute.get("response");
			
			return OauthAttributes.builder()
					.name((String) response.get("name")) 
					.email((String) response.get("email"))
					.attribute(attribute)
					.nameAttributekey(usernameAttributename)
					.build();
	}
		
	// Service에서 동일 메인이 없다면 회원가입이 진행되도록 하는 Method
	public UserVO toEntity() {
		return UserVO.builder().userName(name).userEmail(email).urole(UserRoleEnumType.USER).build();
	}
		
}
