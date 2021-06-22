package com.kosta.finalProject.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

// Controller에서 socialLoginType 파람을 받을 때, enum 타입의 대문자 값을
// 소문자로도 mapping이 가능하도록 만들어진 클래스입니다 선생님들
// Converter를 구현하지 않는 경우에는, 주소 파라미터가 무조건 대문자 형태의 파라미터로 되어야 하기 때문에 그렇습니다.
// ex. Converter 없음 -> http://localhost:8888/auth/GOOGLE 로만 갈 수밖에 없음

@Configuration
public class SocialLoginTypeConverter implements Converter<String, SocialLoginType> {
	@Override
	public SocialLoginType convert(String s) {
		return SocialLoginType.valueOf(s.toUpperCase());
	}
}
