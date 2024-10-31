package com.example.sonaki.board;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BoardForm {
	
	@NotEmpty(message = "제목을 입력해 주세요.")
	@Size(max = 100)
	private String title;
	
	@NotEmpty(message = "내용을 입력해 주세요.")
	private String content;
	
}
