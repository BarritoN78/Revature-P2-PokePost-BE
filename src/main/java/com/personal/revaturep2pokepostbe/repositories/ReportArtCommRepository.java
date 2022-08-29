package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportArtComm;

@Repository
public interface ReportArtCommRepository extends JpaRepository<ReportArtComm, Integer>{

	/**
	 * Deletes a report on a given comment made by a given user
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean deleteByCommentIDAndUserID(int commentID, int userID);

}
