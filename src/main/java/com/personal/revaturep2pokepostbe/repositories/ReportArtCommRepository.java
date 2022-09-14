package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface ReportArtCommRepository extends JpaRepository<ReportArtComm, Integer>{

	/**
	 * Returns a boolean based on if a report on a given fanart made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(ArtCommIDDTO commentID, UserIDDTO userID);
	
	/**
	 * Deletes a report on a given comment made by a given user
	 * @param artCommIDDTO
	 * @param userIDDTO
	 * @return
	 */
	int deleteByCommentIDAndUserID(ArtCommIDDTO commentID, UserIDDTO userID);

}
