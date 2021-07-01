package com.kosta.finalProject.login.oauth;

import java.util.Map;

import com.kosta.finalProject.models.UserRoleEnumType;
import com.kosta.finalProject.models.UserVO;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OauthAttributes {
	
	private Map<String, Object> attribute; // 회원정보
	private String nameAttributekey; // 요청 정보의 키
	private String name;
	private String email;
	
	@Builder
	public OauthAttributes(Map<String, Object> attribute, String nameAttributekey, String name, String email) {
		super();
		this.attribute = attribute;
		this.nameAttributekey = nameAttributekey;
		this.name = name;
		this.email = email;
	} 
	
	// sns 구분 메소드 (service의 of 메소드와 연결)
	public static OauthAttributes of(String registrationId, String userNameAttributename, Map<String, Object> attribute) {
		// 소셜 정보에 따른 구분
		if(registrationId.equals("kakao")) 
			return ofKakao(userNameAttributename, attribute);
		else if(registrationId.equals("naver")) 
			return ofNaver(userNameAttributename, attribute);
		else
			return ofGoogle(userNameAttributename, attribute);
	}
	
	// 구글 관련 정보를 가져오는 메소드
	public static OauthAttributes ofGoogle(String userNameAttributename, Map<String, Object> attribute) {
		return OauthAttributes.builder()
				.name((String) attribute.get("name")) // 여기서 "name"은 UserVO의 userName이 아닌, google 정보에서의 name
				.email((String) attribute.get("email"))
				.attribute(attribute)
				.nameAttributekey(userNameAttributename)
				.build();
	}
	
	// 카카오 정보
	private static OauthAttributes ofKakao(String userNameAttributename, Map<String, Object> attribute) {
		// 요청 정보로 객체를 생성
		Map<String, Object> kakaoAccount = (Map<String, Object>) attribute.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
		
		return OauthAttributes.builder()
				.name((String)profile.get("nickname"))
				.email((String) kakaoAccount.get("email"))
				.attribute(attribute)
				.nameAttributekey(userNameAttributename)
				.build();
	}
	
	// 네이버 정보
	private static OauthAttributes ofNaver(String userNameAttributename, Map<String, Object> attribute) {
		// 요청 정보로 객체 생성
		Map<String, Object> response = (Map<String, Object>) attribute.get("response");
		
		return OauthAttributes.builder()
				.name((String)response.get("name"))
				.email((String) response.get("email"))
				.attribute(attribute)
				.nameAttributekey(userNameAttributename)
				.build();
	}
	
	// service에서 동일 메일이 DB에 존재하지 않음? -> 회원 가입이 진행되도록 하는 메소드
	public UserVO toEntity() { // 기본 권한은 user로 그냥 통일
		return UserVO.builder().name(name).email(email).role(UserRoleEnumType.USER).build();
	}
	
}
