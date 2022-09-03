package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when a user tries to make/update an account 
 * with a username that already exists.
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="The email entered already exists")
public class EmailAlreadyExistsException extends Exception {
	public EmailAlreadyExistsException() {
        super ("Email already exists in the database!");
	}
	
	public EmailAlreadyExistsException(String email) {
        super ("Email["+ email +"] already exists in the database!");
	}
}
