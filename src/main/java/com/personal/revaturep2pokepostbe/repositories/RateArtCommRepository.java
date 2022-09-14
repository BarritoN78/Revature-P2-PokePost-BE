package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface RateArtCommRepository extends JpaRepository<RateArtComm, Integer>{

	/**
	 * Returns a boolean based on if a rating on a given fanart comment made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(ArtCommIDDTO commentID, UserIDDTO userID);
	
	/**
	 * Deletes a rating on a given fanart comment made by a given user
	 * @param commentID
	 * @param userID
	 * @return
	 */
	int deleteByCommentIDAndUserID(ArtCommIDDTO commentID, UserIDDTO userID);

}
