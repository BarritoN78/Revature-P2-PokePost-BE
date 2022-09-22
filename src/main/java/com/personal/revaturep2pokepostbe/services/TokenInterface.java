package com.personal.revaturep2pokepostbe.services;

import java.util.Optional;

import com.personal.revaturep2pokepostbe.exceptions.AuthenticationFailedException;
import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;

/**
 * An interface to be used by TokenService
 * @author Barry Norton
 *
 */
public interface TokenInterface {
	
	/**
	 * Creates and returns a JWT based on a given user
	 * @param user
	 * @return
	 */
	public String createToken(UserDTO user);
	
	/**
	 * Validates a jwt token and passes back the user object related to the token
	 * @param token
	 * @return
	 */
	public Optional<User> validateToken(String token) throws AuthenticationFailedException;
}
