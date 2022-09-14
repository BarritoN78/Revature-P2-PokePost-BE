package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when a page cannot be found
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="The server failed to save your data")
public class SaveFailedException extends Exception {
	public SaveFailedException() {
        super ("The server failed to save your data!");
	}
}
