package com.dasolute.jojoldo.book.web;

import com.dasolute.jojoldo.book.service.PostsService;
import com.dasolute.jojoldo.book.web.dto.PostsResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

	@Autowired
	private PostsService postsService;

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("posts", postsService.findAllDesc());
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
