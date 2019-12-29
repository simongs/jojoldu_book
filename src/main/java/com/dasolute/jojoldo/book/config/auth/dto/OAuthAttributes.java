package com.dasolute.jojoldo.book.config.auth.dto;

import com.dasolute.jojoldo.book.domain.user.Role;
import com.dasolute.jojoldo.book.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Getter
public class OAuthAttributes {

	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;

	@Builder
	public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}

	public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
		if (StringUtils.equals(registrationId, "naver")) {
			// response > id 가 유저를 식별할 수 있는 정보이다.
			return ofNaver("id", attributes);
		}

		return ofGoogle(userNameAttributeName, attributes);
	}

	private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
		Map<String, Object> responseMap = (Map<String, Object>)attributes.get("response");


		return OAuthAttributes.builder()
				.name(MapUtils.getString(responseMap, "name"))
				.email(MapUtils.getString(responseMap, "email"))
				.picture(MapUtils.getString(responseMap, "profile_image"))
				.attributes(responseMap)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}

	public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
		return OAuthAttributes.builder()
				.name(MapUtils.getString(attributes, "name"))
				.email(MapUtils.getString(attributes, "email"))
				.picture(MapUtils.getString(attributes, "picture"))
				.attributes(attributes)
				.nameAttributeKey(userNameAttributeName)
				.build();
	}


	public User toEntity() {
		return User.builder()
				.name(name)
				.email(email)
				.picture(picture)
				.role(Role.GUEST)
				.build();
	}
}
