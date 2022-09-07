package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when PokeAPI returns a 404
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="A record with the given id could not be found")
public class RecordNotFoundException extends Exception {
	public RecordNotFoundException() {
        super ("A record with the given id could not be found!");
	}
	
	public RecordNotFoundException(String type, int id) {
        super ( type + ": Record["+ id +"] could not be found!");
	}
}
