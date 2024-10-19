package com.example.sonaki.user;

import lombok.Getter;

@Getter
public enum UserRole {
	
	ADMIN("ROLE_ADMIN"), USER("ROLE_USER"); // ADMIN 이라면 ROLE_ADMIN 이라는 권한을, USER라면 ROLE_USER라는 권한을
	
	UserRole(String value){
		this.value = value;
	}
	
	private String value; // 외부에서 값이 들어오면
	
	
}
