package com.ddu.home.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ddu.home.entity.User;
import com.ddu.home.service.UserService;

@RestController
@RequestMapping("api/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@PostMapping("/signup")
	public String signup (@RequestParam("username") String username,@RequestParam("password") String password) {
		
	 	User user = userService.signupUser(username, password);
	 	
	 	return "회원가입 완료 : " + user.getUsername();
	}
	
	//로그인 후 인증을 받은 유저만 접근할 수 있는 요청
	@GetMapping("/apicheck")
	public String check () {
		return "로그인 성공 확인 !";
	}
	@GetMapping("/me")
	public ResponseEntity<?> me(Authentication auth) {
		if (auth == null) {
			return ResponseEntity.status(401).body(Map.of("error", "로그인이 필요합니다"));
		}
		return ResponseEntity.ok(Map.of("username", auth.getName()));
	}
	
}
