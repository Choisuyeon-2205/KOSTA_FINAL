package com.kosta.finalProject.login;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SocialLoginType {
	GOOGLE("구글"),
	FACEBOOK("페이스북"),
	KAKAO("카카오"),
	NAVER("네이버"),
	GITHUB("깃허브");
	
	private final String title;
}
