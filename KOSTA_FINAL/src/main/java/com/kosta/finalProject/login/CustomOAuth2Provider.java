package com.kosta.finalProject.login;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

public enum CustomOAuth2Provider {
	FACEBOOK{
		@Override
		public ClientRegistration.Builder getBuilder(String registrationId){
			ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.POST, DEFAULT_LOGIN_REDIRECT_URL);
			builder.scope(new String[]{"email", "public_profile"});
			builder.authorizationUri("https://www.facebook.com/dialog/oauth");
			builder.tokenUri("https://graph.facebook.com/v3.0/oauth/access_token");
			builder.userInfoUri("https://graph.facebook.com/me");
			builder.clientName("Facebook");
			return builder;
			}
	}, 
	GOOGLE {

	        @Override
	        public ClientRegistration.Builder getBuilder(String registrationId) {
	            ClientRegistration.Builder builder = getBuilder(registrationId,
	                    ClientAuthenticationMethod.BASIC, DEFAULT_LOGIN_REDIRECT_URL);
	            builder.scope("profile", "email");
	            builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
	            builder.tokenUri("https://www.googleapis.com/oauth2/v4/token");
	            builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
	            builder.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo");
	            builder.userNameAttributeName(IdTokenClaimNames.SUB);
	            builder.clientName("Google");
	            return builder;
	            }
	},
	KAKAO{
		@Override
		public ClientRegistration.Builder getBuilder(String registrationId){
			ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.POST, DEFAULT_LOGIN_REDIRECT_URL);
			builder.scope(new String[]{"profile", "account_email"});
			builder.authorizationUri("https://kauth.kakao.com/oauth/authorize");
			builder.tokenUri("https://kauth.kakao.com/oauth/token");
			builder.userInfoUri("https://kapi.kakao.com/v2/user/me");
			builder.clientName("Kakao");
			return builder;
		}
	}, 
	NAVER{
		@Override
		public ClientRegistration.Builder getBuilder(String registrationId){
			ClientRegistration.Builder builder = getBuilder(registrationId, ClientAuthenticationMethod.POST, DEFAULT_LOGIN_REDIRECT_URL);
			builder.scope(new String[]{"name", "email"});
			builder.authorizationUri("https://nid.naver.com/oauth2.0/authorize");
			builder.tokenUri("https://nid.naver.com/oauth2.0/token");
			builder.userInfoUri("https://openapi.naver.com/v1/nid/me");
			builder.clientName("Naver");
			return builder;
		}
	};
	
	
	
	private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/login/oauth2/code/{registrationId}";
	
	protected final ClientRegistration.Builder getBuilder(String registrationId, ClientAuthenticationMethod method, String redirectUri){
		ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
		builder.clientAuthenticationMethod(method);
		builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
		builder.redirectUriTemplate(redirectUri);
		return builder;
	}
	
	public abstract ClientRegistration.Builder getBuilder(String registrationId);
	
}
