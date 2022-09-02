package com.personal.revaturep2pokepostbe.services;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.personal.revaturep2pokepostbe.models.Ability;
import com.personal.revaturep2pokepostbe.models.Move;
import com.personal.revaturep2pokepostbe.models.Pokemon;

/**
 * An interface to be followed by PokemonService
 * @author Barry Norton
 *
 */
public interface PokemonInterface {

	/**
	 * Retrieves a pokemon's statistics from PokeAPI given the pokemon's id or name
	 * @param pokemon
	 * @return
	 */
	public Pokemon getPokemon(String pokemon);
	
	/**
	 * Converts the JSON string provided by the PokeAPI into a Pokemon object
	 * @param pokemonJSON
	 * @return
	 */
	public Pokemon createPokemonObject(String pokemonJSON);
	
	/**
	 * Simplifies the types data provided from the PokeAPI down to a string array of type names
	 * @param node
	 * @return
	 */
	public String[] getTypes(JsonNode node);
	
	/**
	 * Simplifies the types data provided from the PokeAPI down to a map of stat names and values
	 * @return
	 */
	public Map<String, Integer> getBaseStats(JsonNode node);
	
	/**
	 * Converts the abilities data provided by the PokeAPI into a list of Ability objects
	 * @param node
	 * @return
	 */
	public List<Ability> getAbilities(JsonNode node);

	/**
	 * Converts the moves data provided by the PokeAPI into a list of Move objects
	 * @param node
	 * @return
	 */
	public List<Move> getMoves(JsonNode node);
}
