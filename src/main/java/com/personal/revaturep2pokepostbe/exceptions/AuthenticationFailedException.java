package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when PokeAPI returns a 404
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The email or password entered is invalid")
public class AuthenticationFailedException extends Exception {
	public AuthenticationFailedException() {
        super ("The email or password entered is invalid!");
	}
	
	public AuthenticationFailedException(String email) {
        super ("The email["+ email +"] or password entered is invalid!");
	}
}
