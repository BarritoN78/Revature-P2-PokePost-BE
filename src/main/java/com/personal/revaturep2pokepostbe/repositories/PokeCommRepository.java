package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.dtos.PokemonIDDTO;

@Repository
public interface PokeCommRepository extends JpaRepository<PokeComment, Integer>{

	/**
	 * Retrieves a page of comments associated with a given pokemon
	 * @param pokemonIDDTO
	 * @param pageParams
	 * @return
	 */
	Page<PokeComment> findByPokeId(PokemonIDDTO pokemonIDDTO, Pageable pageParams);

}
