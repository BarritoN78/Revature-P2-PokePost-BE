package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when a record with the given email and password cannot be found
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason="The current user cannot perform this action")
public class UnauthorizedException extends Exception {
	public UnauthorizedException() {
        super ("The current user cannot perform this action!");
	}
	
	public UnauthorizedException(String reason) {
		super (reason);
	}
}
