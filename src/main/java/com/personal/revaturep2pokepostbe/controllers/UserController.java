package com.personal.revaturep2pokepostbe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;
import com.personal.revaturep2pokepostbe.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userServ;
	
	public UserController(UserService userServ) {
		this.userServ = userServ;
	}	
	
	public ResponseEntity<UserDTO> registerUser(@RequestBody User newUser){
		User result = userServ.registerUser(newUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	public ResponseEntity<Boolean> deleteUser(@PathVariable int userID){
		boolean result = userServ.deleteUser(userID);
		return ResponseEntity.ok(result);
	}
	
	public ResponseEntity<UserDTO> updateUserDetails(@RequestBody UserDTO updatedUser){
		User result = userServ.updateUserDetails(updatedUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	public ResponseEntity<UserDTO> updateUserPassword(@RequestBody User updatedUser){
		User result = userServ.updateUserPassword(updatedUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	public ResponseEntity<UserDTO> getUser(@PathVariable int userID){
		User result = userServ.getUser(userID);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
}
