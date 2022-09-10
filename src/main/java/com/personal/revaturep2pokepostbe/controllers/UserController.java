package com.personal.revaturep2pokepostbe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.EmailAlreadyExistsException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.UsernameAlreadyExistsException;
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
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User newUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException{
		User result = userServ.registerUser(newUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	@DeleteMapping("/{userID}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable int userID) throws RecordNotFoundException{
		boolean result = userServ.deleteUser(userID);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/update/details")
	public ResponseEntity<UserDTO> updateUserDetails(@RequestBody UserDTO updatedUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException{
		User result = userServ.updateUserDetails(updatedUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	@PostMapping("/update/password")
	public ResponseEntity<UserDTO> updateUserPassword(@RequestBody User updatedUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException{
		User result = userServ.updateUserPassword(updatedUser);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<UserDTO> getUser(@PathVariable int userID) throws RecordNotFoundException{
		User result = userServ.getUser(userID);
		UserDTO returnDTO = new UserDTO(result);
		return ResponseEntity.ok(returnDTO);
	}
}
