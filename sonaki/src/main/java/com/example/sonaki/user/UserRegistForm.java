package com.example.sonaki.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistForm {
	
	@NotEmpty(message = "아이디 입력은 필수입니다.")
	@Size(min = 3, max = 20, message = "3~20 글자 입력 요망")
	private String username;
	
	@NotEmpty(message = "비밀번호 입력은 필수입니다.")
	private String password;
	
	@NotEmpty(message = "이메일 입력은 필수입니다.")
	@Email
	private String email;
	
}
