package com.example.sonaki.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String freeboard(Model model) {
		
		List<Board> boardList = this.boardService.getFreeList();
		model.addAttribute("free", boardList);
		return "freeboard";
	}
	
	@GetMapping("/form")
	public String boardform(BoardForm boardForm) {
		return "board_form";
	}
	
	@GetMapping("/detail/{id}")
	public String getBoard(Model model, @PathVariable("id") Integer id) {
		Board board = this.boardService.getBoard(id);
		model.addAttribute("board", board);
		return "board_detail";
	}
	
	@PostMapping("/create")
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {			
			model.addAttribute("boardForm", boardForm);
			return "board_form";
		}
		System.out.println(boardForm.getTitle());
		System.out.println(boardForm.getContent());
		this.boardService.create(boardForm.getTitle(), boardForm.getContent());
		return "redirect:/board/free";
	}
	
}
