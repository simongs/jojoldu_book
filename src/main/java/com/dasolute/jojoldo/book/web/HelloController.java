package com.dasolute.jojoldo.book.web;

import com.dasolute.jojoldo.book.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public String helloWorld() {
		return "helloWorld";
	}

	@GetMapping("hello/dto")
	public HelloResponseDto helloResponseDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
		return HelloResponseDto.builder()
				.name(name)
				.amount(amount)
				.build();
	}
}
