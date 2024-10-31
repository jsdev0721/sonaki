package com.example.sonaki.board;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/list")
	public String boardList() {
		return "board";
	}
	
	@GetMapping("/free")
	public String freeboard() {
		return "freeboard";
	}
	
	@GetMapping("/form")
	public String boardform(BoardForm boardForm) {
		return "board_form";
	}
	
	@PostMapping("/create")
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {			
			model.addAttribute("boardForm", boardForm);
			return "board_form";
		}
		System.out.println(boardForm.getTitle());
		System.out.println(boardForm.getContent());
		return "redirect:/board/free";
	}
	
}
