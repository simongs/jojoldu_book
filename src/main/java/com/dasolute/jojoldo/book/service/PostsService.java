package com.dasolute.jojoldo.book.service;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import com.dasolute.jojoldo.book.domain.posts.PostsRepository;
import com.dasolute.jojoldo.book.web.dto.PostsResponseDto;
import com.dasolute.jojoldo.book.web.dto.PostsSaveRequestDto;
import com.dasolute.jojoldo.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostsService {

	private final PostsRepository postsRepository;

	public Long save(PostsSaveRequestDto requestDto) {
		Posts entity = postsRepository.save(requestDto.convertEntity());
		return entity.getId();
	}

	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

		entity.update(requestDto.getTitle(), requestDto.getContent());

		return id;
	}

	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

		return new PostsResponseDto(entity);
	}
}
