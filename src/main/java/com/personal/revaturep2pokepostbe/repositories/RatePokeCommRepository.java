package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.RatePokeComm;

@Repository
public interface RatePokeCommRepository extends JpaRepository<RatePokeComm, Integer>{

	/**
	 * Returns a boolean based on if a rating on a given pokemon comment made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(int commentID, int userID);
	
	/**
	 * Deletes a rating on a given pokemon comment made by a given user
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean deleteByCommentIDAndUserID(int commentID, int userID);

}
