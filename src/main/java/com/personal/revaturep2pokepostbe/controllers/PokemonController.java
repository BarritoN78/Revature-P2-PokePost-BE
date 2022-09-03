package com.personal.revaturep2pokepostbe.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.PokemonNotFoundException;
import com.personal.revaturep2pokepostbe.models.Pokemon;
import com.personal.revaturep2pokepostbe.services.PokemonService;

@RestController
@RequestMapping("pokemon")
public class PokemonController {
	private final PokemonService pokeServ;
	
	public PokemonController(PokemonService pokeServ) {
		this.pokeServ = pokeServ;		
	}
	
	@GetMapping("/{pokeId}")
	public ResponseEntity<Pokemon> getPokemon(@PathVariable String pokeId) throws PokemonNotFoundException{
		   Pokemon result = pokeServ.getPokemon(pokeId);
			return ResponseEntity.ok(result);
	}
}
