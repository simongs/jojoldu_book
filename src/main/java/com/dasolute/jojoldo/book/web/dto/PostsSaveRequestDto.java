package com.dasolute.jojoldo.book.web.dto;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

	private String author;
	private String title;
	private String content;

	@Builder
	public PostsSaveRequestDto(String author, String title, String content) {
		this.author = author;
		this.title = title;
		this.content = content;
	}

	public Posts convertEntity() {
		return Posts.builder()
				.author(getAuthor())
				.title(getTitle())
				.content(getContent())
				.build();
	}
}
