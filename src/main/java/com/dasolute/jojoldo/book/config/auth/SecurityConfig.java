package com.dasolute.jojoldo.book.config.auth;

import com.dasolute.jojoldo.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 관련 설정을 활성화 시켜준다
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // h2-console 화면 사용을 위해 disable
			.headers().frameOptions().disable() // h2-console 화면 사용을 위해 disable
			.and()
				.authorizeRequests() // URL 별 권한 관리를 설정하는 옵션의 시작점, authorizeRequests 선언되어야 antMatchers 사용가능
				.antMatchers("/", // 권힌 관리 대상을 지정, URL, HTTP 메소드별 가능
						"/css/**",
						"/images/**",
						"/js/**",
						"/h2-console/**").permitAll()
				.antMatchers("/api/v1/**").hasRole(Role.USER.name())
				.anyRequest().authenticated() // 설정된 값 외의 모든 요청, 나머지 URL은 모두 인증된 사용자만 허용한다. (로그인된 사용자)
			.and()
				.logout() // 로그아웃 성공시 /로 이동
				.logoutSuccessUrl("/")
			.and()
				.oauth2Login() // oAuth2 로그인 기능에 대한 설정
				.userInfoEndpoint() // 로그인 성공 후에 사용자 정보를 가져올때의 설정들을 담당
				.userService(customOAuth2UserService) // 소셜 로그인 성공시 후속조치를 진행할 userService 등록
		                                              // 리소스 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시
		;

	}
}
