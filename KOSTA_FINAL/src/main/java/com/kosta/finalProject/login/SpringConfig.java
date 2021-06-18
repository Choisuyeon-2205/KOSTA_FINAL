package com.kosta.finalProject.login;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringConfig implements WebMvcConfigurer {

	@Bean
	 public MessageSource messageSource() {
        Locale.setDefault(Locale.KOREA); 
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();

        messageSource.setDefaultEncoding("UTF-8"); 
        messageSource.setBasenames("classpath:message/security_message", "classpath:org/springframework/security/messages"); // 而ㅼ뒪���븳 properties �뙆�씪, security properties �뙆�씪 �닚�꽌��濡� �꽕�젙
        return messageSource;
    }
}
