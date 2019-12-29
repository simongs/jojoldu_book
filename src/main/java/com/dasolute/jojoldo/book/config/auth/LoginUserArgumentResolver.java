package com.dasolute.jojoldo.book.config.auth;

import com.dasolute.jojoldo.book.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;


// 이렇게 커스터마이징된 ArgumentResolver는 WebMvcConfigurer에 추가를 합니다.
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	private final HttpSession httpSession;

	// 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단합니다.
	// 여기서는 @LoginUser 어노테이션이 붙어있고, 파라미터 클래스가 SessionUser 인 경우 true를 반환합니다.
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());

		return isLoginUserAnnotation && isUserClass;
	}

	// 파라미터에 전달한 객체를 생성합니다.
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return httpSession.getAttribute("user");
	}
}
