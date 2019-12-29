package com.dasolute.jojoldo.book.web.dto;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsListResponseDto {
	private Long id;
	private String author;
	private String title;
	private LocalDateTime modifiedDate;

	public PostsListResponseDto(Posts post) {
		this.id = post.getId();
		this.author = post.getAuthor();
		this.title = post.getTitle();
		this.modifiedDate = post.getModifiedDate();
	}
}
