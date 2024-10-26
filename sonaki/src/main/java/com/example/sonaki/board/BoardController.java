package com.example.sonaki.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String boardform() {
		return "board_form";
	}
	
}
