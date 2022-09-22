package com.personal.revaturep2pokepostbe.controllers;

import java.util.Map;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.AuthenticationFailedException;
import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;
import com.personal.revaturep2pokepostbe.services.UserService;

@WebMvcTest
public class AuthControllerTests {
	private final UserService userServ;
	
	public AuthControllerTests(UserService userServ) {
		this.userServ = userServ;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody Map<String, String> credentials) throws AuthenticationFailedException{
		String email = credentials.get("email");
		String password = credentials.get("password");
		User result = userServ.getUserByCredentials(email, password);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
}
