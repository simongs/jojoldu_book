package com.dasolute.jojoldo.book.web;


import com.dasolute.jojoldo.book.domain.posts.PostsRepository;
import com.dasolute.jojoldo.book.service.PostsService;
import com.dasolute.jojoldo.book.web.dto.PostsResponseDto;
import com.dasolute.jojoldo.book.web.dto.PostsSaveRequestDto;
import com.dasolute.jojoldo.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

	private final PostsService postsService;

	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
		return postsService.save(requestDto);
	}

	@PostMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
		return postsService.update(id, requestDto);
	}

	@GetMapping("/api/v1/posts/{id}")
	private PostsResponseDto findById(@PathVariable("id") Long id) {
		return postsService.findById(id);
	}
}
