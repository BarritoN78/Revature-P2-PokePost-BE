package com.personal.revaturep2pokepostbe.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;

@Service
public class TokenService implements TokenInterface{

	@Override
	public String createToken(UserDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<User> validateToken(String token) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}
	
}
