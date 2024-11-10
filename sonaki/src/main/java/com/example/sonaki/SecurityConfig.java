package com.example.sonaki;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests((authorizeHttpRequest) -> authorizeHttpRequest
				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll()) // 모든 url를 시큐리티를 안거치게 해둠
		
		.csrf((csrf) -> csrf
				.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
				.ignoringRequestMatchers(new AntPathRequestMatcher("/image/upload"))) // h2는 csrf(토큰)이 발행이 안됨, csrf 검사를 안하게 해줘서 h2들어갈수잇게해줌
		
		.headers((headers) -> headers
				.addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))) // h2 사이트 방식대로 보이게해줌 (시큐리티는 X-Frame-Options, h2는 DENY 방식)
		
		.formLogin( (formLogin) -> formLogin
				.loginPage("/user/login")
				.defaultSuccessUrl("/") // 로그인 성공하면 "/"(question/list)로 감 
				.failureUrl("/user/login?error=true"))
		
		.logout((logout) -> logout
				.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true))
		;
		return http.build();
	}
	
	@Bean
	PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean //AuthenticationManager : 인증담당 인터페이스(인증한 정보를 저장)//AuthenticationConfiguration : 옆의 인터페이스를 구현한 클래스(인증을 처리함)
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
		return authenticationConfiguration.getAuthenticationManager(); //getAuthenticationManager : 실제 인증정보를 가져옴
	}

}
