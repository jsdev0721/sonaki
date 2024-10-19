package com.example.sonaki;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity Not Found")
public class DataNotFoundException extends RuntimeException{

	private static final long serialVersionID = 1L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
}
