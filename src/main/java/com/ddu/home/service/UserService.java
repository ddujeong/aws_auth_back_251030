package com.ddu.home.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ddu.home.entity.User;
import com.ddu.home.repository.UserRepository;

@Service
//@RequiredArgsConstructor
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	//private final PasswordEncoder passwordEncoder;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User signupUser (String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		return userRepository.save(user);
	}
}
