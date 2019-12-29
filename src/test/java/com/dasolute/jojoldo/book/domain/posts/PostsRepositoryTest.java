package com.dasolute.jojoldo.book.domain.posts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

	@Autowired
	private PostsRepository postsRepository;


	@Test
	public void BaseTimeEntity() {
		// given
		LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

		Posts inserted = postsRepository.save(Posts.builder()
				.author("author")
				.content("content")
				.title("title")
				.build());

		Posts selected = postsRepository.findById(inserted.getId()).get();

		System.out.println(selected);

		// @EnableJpaAuditing 을 활성화해야한다.
		// 그래야 값이 들어간다.ㄴ
		assertThat(selected.getCreatedDate()).isAfter(now);
	}

}