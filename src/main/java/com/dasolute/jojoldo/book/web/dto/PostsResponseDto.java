package com.dasolute.jojoldo.book.web.dto;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

	private Long id;
	private String author;
	private String title;
	private String content;

	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.author = entity.getAuthor();
		this.title = entity.getTitle();
		this.content = entity.getContent();
	}
}
