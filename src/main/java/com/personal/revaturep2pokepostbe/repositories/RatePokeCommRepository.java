package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.dtos.PokeCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface RatePokeCommRepository extends JpaRepository<RatePokeComm, Integer>{

	/**
	 * Returns a boolean based on if a rating on a given pokemon comment made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(PokeCommIDDTO commentID, UserIDDTO userID);
	
	/**
	 * Deletes a rating on a given pokemon comment made by a given user
	 * @param commentID
	 * @param userID
	 * @return
	 */
	int deleteByCommentIDAndUserID(PokeCommIDDTO commentID, UserIDDTO userID);

}
