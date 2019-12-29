package com.dasolute.jojoldo.book.service;

import com.dasolute.jojoldo.book.domain.posts.Posts;
import com.dasolute.jojoldo.book.domain.posts.PostsRepository;
import com.dasolute.jojoldo.book.web.dto.PostsListResponseDto;
import com.dasolute.jojoldo.book.web.dto.PostsResponseDto;
import com.dasolute.jojoldo.book.web.dto.PostsSaveRequestDto;
import com.dasolute.jojoldo.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

	// 트랜잭션 범위를 유지하되, 조회기능만 남겨두어 조회 속도가 개선되기 때문에
	// 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것을 추천한다.
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)
				.collect(Collectors.toList());
	}

	public Long delete(Long id) {
		postsRepository.deleteById(id);
		return id;
	}
}
