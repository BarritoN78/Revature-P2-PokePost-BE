package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when the user has already rated the given entity
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason="The requested pokemon has already been wishlisted by the given user")
public class AlreadyWishlistedException extends Exception {
	public AlreadyWishlistedException() {
        super ("The requested pokemon has already been wishlisted by the given user!");
	}
	
	public AlreadyWishlistedException(int pokeId, int userID) {
        super ( "Pokemon["+ pokeId +"] has already been wishlisted by User[" + userID + "]!");
	}
}
