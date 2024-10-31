package com.example.sonaki.board;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public Board create(String title, String content) {
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setCreateDate(LocalDateTime.now());
		return this.boardRepository.save(board);
	}
	
	public List<Board> getFreeList(){
		return this.boardRepository.findAll();
	}
	
	public Board getBoard(Integer id) {
		Optional<Board> ob = this.boardRepository.findById(id);
		if(ob.isPresent()) {
			return ob.get();
		}else {
			return null;
		}
	}
	
}
