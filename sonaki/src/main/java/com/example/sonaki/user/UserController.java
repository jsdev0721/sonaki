package com.example.sonaki.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
    @GetMapping("/login")
    public String login() {
        return "main";
    }
	
	@GetMapping("/regist")
	public String regist(UserRegistForm userRegistForm) {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(@Valid UserRegistForm userRegistForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "regist";
		}
		
		this.userService.regist(userRegistForm.getUsername(), userRegistForm.getPassword(), userRegistForm.getEmail());
		
		return "main";
	}
	
}
