package com.dasolute.jojoldo.book.web;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import com.dasolute.jojoldo.book.domain.posts.PostsRepository;
import com.dasolute.jojoldo.book.web.dto.PostsSaveRequestDto;
import com.dasolute.jojoldo.book.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private PostsRepository postsRepository;

	@After
	public void cleanUp() {
		postsRepository.deleteAll();
	}

	@Test
	public void insertTest() throws Exception {
		// given
		String title = "title";
		String content = "content";

		PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
				.author("author")
				.title(title)
				.content(content)
				.build();

		String url = "http://localhost:" + port + "/api/v1/posts";

		//when
		ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

		// then
		assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(responseEntity.getBody()).isGreaterThan(0L);

		Optional<Posts> selected = postsRepository.findById(responseEntity.getBody());

		assertThat(selected.get().getTitle()).isEqualTo(title);
		assertThat(selected.get().getContent()).isEqualTo(content);
	}

	@Test
	public void updateTest() {
		//given
		Posts savePosts = postsRepository.save(Posts.builder()
				.title("title")
				.content("content")
				.author("author")
				.build());

		Long updateId = savePosts.getId();
		String expectedTitle = "title2";
		String expectedContent = "content2";

		PostsUpdateRequestDto updateRequestDto = PostsUpdateRequestDto.builder()
				.content(expectedContent)
				.title(expectedTitle)
				.build();

		// when
		String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;
		ResponseEntity<Long> updateResponse = restTemplate.postForEntity(url, updateRequestDto, Long.class);

		// then
		assertThat(updateResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(updateResponse.getBody()).isGreaterThan(0L);


	}


}