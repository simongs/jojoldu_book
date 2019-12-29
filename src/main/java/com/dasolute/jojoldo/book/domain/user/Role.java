package com.dasolute.jojoldo.book.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

	// Spring Security 에서 role명은 반드시 ROLE_ 로 시작해야 한다.
	GUEST("ROLE_GUEST", "손님"),
	USER("ROLE_USER", "일반사용자");

	private final String key;
	private final String title;

}
