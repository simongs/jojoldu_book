package com.dasolute.jojoldo.book.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

	/**
	 * assertThat 은 assertj 라이브러리를 통해서 진행한다.
	 */
	@Test
	public void lombok_test() {
		// given
		String name = "test";
		int amount = 10;

		// when
		HelloResponseDto dto = HelloResponseDto
				.builder()
				.name(name).amount(amount)
				.build();

		// then
		assertThat(dto.getName()).isEqualTo(name);
		assertThat(dto.getAmount()).isEqualTo(amount);
	}

}