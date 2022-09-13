package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.RateArtComm;

@Repository
public interface RateArtCommRepository extends JpaRepository<RateArtComm, Integer>{

	/**
	 * Returns a boolean based on if a rating on a given fanart comment made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(int commentID, int userID);
	
	/**
	 * Deletes a rating on a given fanart comment made by a given user
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean deleteByCommentIDAndUserID(int commentID, int userID);

}
