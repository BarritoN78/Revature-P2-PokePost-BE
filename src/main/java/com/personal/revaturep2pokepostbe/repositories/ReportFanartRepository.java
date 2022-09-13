package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface ReportFanartRepository extends JpaRepository<ReportFanart, Integer>{
	
	/**
	 * Returns a boolean based on if a report on a given fanart made by a given user exists
	 * @param artID
	 * @param userID
	 * @return
	 */
	boolean existsByArtIDAndUserID(int artID, int userID);
	
	/**
	 * Deletes a report on a given fanart made by a given user
	 * @param artID
	 * @param userID
	 * @return
	 */
	boolean deleteByArtIDAndUserID(ArtIDDTO artID, UserIDDTO userID);
}
