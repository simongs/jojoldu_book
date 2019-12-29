package com.dasolute.jojoldo.book.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class HelloResponseDto {

	private String name;
	private int amount;

	@Builder
	public HelloResponseDto(String name, int amount) {
		this.name = name;
		this.amount = amount;
	}
}
