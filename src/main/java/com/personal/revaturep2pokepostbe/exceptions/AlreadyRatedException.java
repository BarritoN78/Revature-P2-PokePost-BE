package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when the user has already rated the given entity
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="The requested entity has already been rated by the given user")
public class AlreadyRatedException extends Exception {
	public AlreadyRatedException() {
        super ("The requested entity has already been rated by the given user!");
	}
	
	public AlreadyRatedException(String type, int id, int userID) {
        super ( type + "["+ id +"] has already been rated by User[" + userID + "]!");
	}
}
