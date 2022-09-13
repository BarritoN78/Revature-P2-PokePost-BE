package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportPokeComm;

@Repository
public interface ReportPokeCommRepository extends JpaRepository<ReportPokeComm, Integer>{

	/**
	 * Returns a boolean based on if a report on a given pokemon comment made by a given user exists
	 * @param commentID
	 * @param userID
	 * @return
	 */
	boolean existsByCommentIDAndUserID(int commentID, int userID);
	
	/**
	 * Deletes a report on a given pokemon comment made by a given user
	 * @param artID
	 * @param userID
	 * @return
	 */
	boolean deleteByCommentIDAndUserID(int commentID, int userID);

}
