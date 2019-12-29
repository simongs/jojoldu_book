package com.dasolute.jojoldo.book.web;

import com.dasolute.jojoldo.book.config.auth.dto.SessionUser;
import com.dasolute.jojoldo.book.service.PostsService;
import com.dasolute.jojoldo.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

	private final PostsService postsService;
	private final HttpSession httpSession;


	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());

		// 로그인을 성공하면 CustomOAuth2UserService에서 session에 저장하도록 했다.
		SessionUser user = (SessionUser) httpSession.getAttribute("user");

		if (user != null) {
			model.addAttribute("userName", user.getName());
		}
		return "index";
	}

	@GetMapping("/posts/save")
	public String save() {
		return "posts-save";
	}

	@GetMapping("posts/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("post", postsService.findById(id));
		return "posts-update";
	}

}
