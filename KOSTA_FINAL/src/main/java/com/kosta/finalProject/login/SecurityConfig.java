package com.kosta.finalProject.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.kosta.finalProject.services.CustomOAuth2UserService;
import com.kosta.finalProject.services.LoginService;

import static com.kosta.finalProject.login.SocialLoginType.*;

import lombok.extern.java.Log;

@Log
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
	
	@Autowired
	 LoginService loginService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();   //Spring Security에서 제공하는 비밀번호 암호화 객체
	}
	
	@Bean
	public ClientRegistrationRepository clientRegistrationRepository() {

	        List<ClientRegistration> clientRegistrations = new ArrayList<>();

	        String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";

	        ClientRegistration.Builder naverBuilder = ClientRegistration.withRegistrationId("naver");
	        naverBuilder.clientAuthenticationMethod(ClientAuthenticationMethod.POST);
	        naverBuilder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
	        naverBuilder.redirectUriTemplate(DEFAULT_LOGIN_REDIRECT_URL);
	        naverBuilder.authorizationUri("https://nid.naver.com/oauth2.0/authorize");
	        naverBuilder.tokenUri("https://nid.naver.com/oauth2.0/token");
	        naverBuilder.jwkSetUri("temp");
	        naverBuilder.userInfoUri("https://openapi.naver.com/v1/nid/me");
	        naverBuilder.clientName("Naver");
	        naverBuilder.scope("profile");
	        naverBuilder.clientId("9iiyHNQAbmjBauijmx4n");
	        naverBuilder.clientSecret("v6MLADqN8U");

	        clientRegistrations.add(naverBuilder.build());
	        
	        ClientRegistration.Builder kakaoBuilder = ClientRegistration.withRegistrationId("kakao");
	        kakaoBuilder.clientAuthenticationMethod(ClientAuthenticationMethod.POST);
	        kakaoBuilder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
	        kakaoBuilder.redirectUriTemplate(DEFAULT_LOGIN_REDIRECT_URL);
	        kakaoBuilder.authorizationUri("https://kauth.kakao.com/oauth/authorize");
	        kakaoBuilder.tokenUri("https://kauth.kakao.com/oauth/token");
	        kakaoBuilder.jwkSetUri("temp");
	        kakaoBuilder.userInfoUri("https://kapi.kakao.com/v2/user/me");
	        kakaoBuilder.clientName("Kakao");
	        kakaoBuilder.scope("profile");
	        kakaoBuilder.clientId("0e59a43c7e7504cf28b6635941f1b53f");
	        kakaoBuilder.clientSecret("KceoqRPtAMBebliMoHHBbS6cc2gNtkBS");
	        
	        clientRegistrations.add(kakaoBuilder.build());
	        
	        return new InMemoryClientRegistrationRepository(clientRegistrations);
	}
	
	
	@Override  //WebSecurity를 통해 HTTP 요청에 대한 웹 기반 보안을 구성
	public void configure(WebSecurity web) throws Exception {
		// 파일 기준은 resources/static 디렉터리의 하위 파일 목록은 인증 무시 ( = 항상통과 )
		web.ignoring().antMatchers("/css/**", "/js/**", "/image/**", "/lib/**");
	}

	protected void configure(HttpSecurity http) throws Exception {
		log.info("security config..........");
		// antMatchers url 패턴에 대한 접근허용
		// permitAll: 모든사용자가 접근가능하다는 의미
		// hasRole : 특정권한을 가진 사람만 접근가능하다는 의미
		http.authorizeRequests() // HttpServletRequest에 따라 접근(access)을 제한

			.antMatchers("/main/**","/fragments/**","/center/**","/webfonts/**","/login/**","/exerciseinfoboard/**","/dietdiaryboard/**","/healthboard/**","/sharingboard/**","/naversearch/**","/auth/**", "/upload/**", "/body/**").permitAll() //   누구나 접근 허용
			.antMatchers("/admin/**").hasRole("ADMIN") // /admin으로 시작하는 경로는  ADMIN롤을 가진 사용자만  접근 가능(자동으로 ROLE_가 삽입)
			.antMatchers("/BUSINESS/**").hasRole("BUSINESS")
			.antMatchers("/facebook").hasAuthority(FACEBOOK.getRoleType())
			.antMatchers("/google").hasAuthority(GOOGLE.getRoleType())
			.antMatchers("/kakao").hasAuthority(KAKAO.getRoleType())
			.antMatchers("/naver").hasAuthority(NAVER.getRoleType())
			.anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
		.and()
			.formLogin() // form 기반으로 인증을 하도록 한다. 로그인 정보는 기본적으로 HttpSession을 이용
			.loginPage("/main") // 로그인 페이지 링크 .... post의 이름이 같다면 loginProcessingUrl생략 	
	          //스프링시큐리티가 해당주소로 오는 요청을 가로채서 대신한다. 
			.defaultSuccessUrl("/main") // 로그인 성공 후 리다이렉트 주소				
			.permitAll()
			.and()
			.logout() // 로그아웃에 관한 설정을 의미
			.logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
			.logoutSuccessUrl("/main") // 로그아웃 성공시 리다이렉트 주소
			.invalidateHttpSession(true)  // 세션 지우기
		.and()
			.csrf().disable()
			.exceptionHandling().accessDeniedPage("/accessDenied")// 403 예외처리 핸들링   권한이 없는 대상이 접속을시도했을 때
	//	.and()
	//		.csrf().disable()  //csrf(크로스사이트 위조요청에 대한 설정) 토큰 비활성화 (test시에는 disable권장)            
	 	.and()
	 		.oauth2Login()
	 		.userInfoEndpoint()
	 		.userService(new CustomOAuth2UserService()); // 네이버 USER INFO의 응답을 처리하기 위한 설정
	}
	
	
	@Override
	  public void configure(AuthenticationManagerBuilder auth) throws Exception { // 9
	    auth.userDetailsService(loginService)
	    	// 해당 서비스(userService)에서는 UserDetailsService를 implements해서 
	        // loadUserByUsername() 구현해야함 (서비스 참고)
	    	.passwordEncoder(new BCryptPasswordEncoder()); 
	   }


//	@Bean
//	public ClientRegistrationRepository clientRegistrationRepository(
//			OAuth2ClientProperties oAuth2ClientProperties,
//			@Value("${custom.oauth2.kakao.client-id}") String kakaoClientId,
//			@Value("${custom.oauth2.kakao.client-secret}") String kakaoClientSecret,
//			@Value("${custom.oauth2.naver.client-id}") String naverClientId,
//			@Value("${custom.oauth2.naver.client-secret}") String naverClientSecret){
//		
//		List<ClientRegistration> registrations = oAuth2ClientProperties
//				.getRegistration().keySet().stream()
//				.map(client->getRegistration(oAuth2ClientProperties, client))
//				.filter(Objects::nonNull)
//				.collect(Collectors.toList());
//		
//		registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
//				.clientId(kakaoClientId)
//				.clientSecret(kakaoClientSecret)
//				.jwkSetUri("temp")
//				.build());
//		
//		registrations.add(CustomOAuth2Provider.NAVER.getBuilder("naver")
//				.clientId(naverClientId)
//				.clientSecret(naverClientSecret)
//				.jwkSetUri("temp")
//				.build());
//		
//		return new InMemoryClientRegistrationRepository(registrations);
//	}
	
	
	private ClientRegistration getRegistration(OAuth2ClientProperties clientProperties, String client) {
		if("google".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("google");
			return CommonOAuth2Provider.GOOGLE.getBuilder(client)
					.clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret())
					.scope("email", "profile")
					.build();
		}
		
		if("facebook".equals(client)) {
			OAuth2ClientProperties.Registration registration = clientProperties.getRegistration().get("facebook");
			return CommonOAuth2Provider.FACEBOOK.getBuilder(client)
					.clientId(registration.getClientId())
					.clientSecret(registration.getClientSecret())
					.userInfoUri("https://graph.facebook.com/me?fields=id,name,email,link")
					.scope("email")
					.build();
		}
		
		return null;
		
	}
	
}