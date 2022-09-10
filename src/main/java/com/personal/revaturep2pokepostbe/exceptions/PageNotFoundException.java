package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when PokeAPI returns a 404
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="No result could be provided for the given page")
public class PageNotFoundException extends Exception {
	public PageNotFoundException() {
        super ("No result could be provided for the given page!");
	}
	
	public PageNotFoundException(int pageNum) {
        super ("No result could be provided for the given page[" + pageNum + "]!");
	}
}
