package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when the user has already reported the given entity
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="The requested entity has already been reported by the given user")
public class AlreadyReportedException extends Exception {
	public AlreadyReportedException() {
        super ("The requested entity has already been reported by the given user!");
	}
	
	public AlreadyReportedException(String type, int id, int userID) {
        super ( type + "["+ id +"] has already been reported by User[" + userID + "]!");
	}
}
