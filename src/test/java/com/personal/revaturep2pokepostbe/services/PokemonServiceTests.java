package com.personal.revaturep2pokepostbe.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.revaturep2pokepostbe.RevatureP2PokepostBeApplication;
import com.personal.revaturep2pokepostbe.exceptions.PokemonNotFoundException;
import com.personal.revaturep2pokepostbe.models.Ability;
import com.personal.revaturep2pokepostbe.models.Move;
import com.personal.revaturep2pokepostbe.models.Pokemon;

@SpringBootTest(classes=RevatureP2PokepostBeApplication.class)
public class PokemonServiceTests implements PokemonInterface {
	private final WebClient client;
	private final ObjectMapper objMapper;

	public PokemonServiceTests(WebClient client) {
		this.client = client;
		this.objMapper = new ObjectMapper();
	}

	@Override
	public Pokemon getPokemon(String pokemon) throws PokemonNotFoundException {
		Pokemon result = null;
		String uri = "https://pokeapi.co/api/v2/pokemon/" + pokemon;

		/* Making call to PokeAPI */
		try {
			String resultJSON = client.get().uri(uri).retrieve().bodyToMono(String.class).block();

			/* Converting returned string into Pokemon object */
			result = createPokemonObject(resultJSON);
		} catch (Exception e) {
			throw new PokemonNotFoundException(pokemon);
		}

		/* Returning result */
		return result;
	}

	@Override
	public Pokemon createPokemonObject(String pokemonJSON) {
		Pokemon pokeObj = new Pokemon();
		try {
			JsonNode pokemonRoot = objMapper.readTree(pokemonJSON);
			pokeObj.setId(pokemonRoot.get("id").asInt());
			pokeObj.setName(pokemonRoot.get("name").asText());
			pokeObj.setImageUrl(
					pokemonRoot.get("sprites").get("other").get("official-artwork").get("front_default").asText());
			pokeObj.setHeight(pokemonRoot.get("height").asInt());
			pokeObj.setWeight(pokemonRoot.get("weight").asInt());
			pokeObj.setTypes(getTypes(pokemonRoot.get("types")));
			pokeObj.setBaseStats(getBaseStats(pokemonRoot.get("stats")));
			pokeObj.setAbilities(getAbilities(pokemonRoot.get("abilities")));
			pokeObj.setMoves(getMoves(pokemonRoot.get("name")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return pokeObj;
	}

	@Override
	public String[] getTypes(JsonNode node) {
		int size = node.size();
		String[] types = new String[size];
		for (int i = 0; i < size; i++) {
			types[i] = node.get(i).get("type").get("name").asText();
		}
		return types;
	}

	@Override
	public Map<String, Integer> getBaseStats(JsonNode node) {
		Map<String, Integer> baseStats = new HashMap<>();
		for (JsonNode jsonNode : node) {
			String baseStatName = jsonNode.get("stat").get("name").asText();
			int baseStatNumber = jsonNode.get("base_stat").asInt();
			baseStats.put(baseStatName, baseStatNumber);
		}
		return baseStats;
	}

	@Override
	public List<Ability> getAbilities(JsonNode node) {
		List<Ability> abilities = new ArrayList<>();
		for (JsonNode jsonNode : node) {
			Ability newAbility = new Ability(jsonNode.get("ability").get("name").asText(),
					jsonNode.get("ability").get("url").asText(), jsonNode.get("slot").asInt(),
					jsonNode.get("is_hidden").asBoolean());
			abilities.add(newAbility);
		}
		return abilities;
	}

	@Override
	public List<Move> getMoves(JsonNode node) {
		List<Move> moves = new ArrayList<>();
		for (JsonNode jsonNode : node) {
			Move newMove = new Move(jsonNode.get("move").get("name").asText());
			moves.add(newMove);
		}
		return moves;
	}

}
