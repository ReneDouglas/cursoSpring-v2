package com.cursospring.spring.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursospring.spring.dto.EmailDTO;
import com.cursospring.spring.security.JWTUtil;
import com.cursospring.spring.security.UserSS;
import com.cursospring.spring.services.AuthService;
import com.cursospring.spring.services.UserService;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Autowired
	private AuthService authService;
	
	@RequestMapping(value = "/refresh", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response){
		UserSS user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO emailDto){
		authService.sendNewPassword(emailDto.getEmail());
		return ResponseEntity.noContent().build();
	}

}
