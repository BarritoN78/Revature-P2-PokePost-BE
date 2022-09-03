package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when a user tries to make/update an account 
 * with a username that already exists.
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="The username entered already exists")
public class UsernameAlreadyExistsException extends Exception {
	public UsernameAlreadyExistsException() {
        super ("Username already exists in the database!");
	}
	
	public UsernameAlreadyExistsException(String username) {
        super ("Username["+ username +"] already exists in the database!");
	}
}
