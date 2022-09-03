package com.personal.revaturep2pokepostbe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception to be thrown when PokeAPI returns a 404
 * @author Barry Norton
 *
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The pokemon entered does not exist")
public class PokemonNotFoundException extends Exception {
	public PokemonNotFoundException() {
        super ("Pokemon does not exist in the PokeAPI!");
	}
	
	public PokemonNotFoundException(String pokemon) {
        super ("Pokemon["+ pokemon +"} does not exist in the PokeAPI!");
	}
}
