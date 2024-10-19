package com.example.sonaki.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Optional<Users> _user = this.userRepository.findByUsername(username);
		if(_user.isEmpty()) {
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		
		Users user = _user.get();
		
		List<GrantedAuthority> ahthorities = new ArrayList<>();
		
		if("admin".equals(username)) {
			ahthorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {
			ahthorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		return new User(user.getUsername(), user.getPassword(), ahthorities);
	}
	
}
