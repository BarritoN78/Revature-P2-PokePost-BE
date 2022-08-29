package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportFanart;

@Repository
public interface ReportFanartRepository extends JpaRepository<ReportFanart, Integer>{
	
	/**
	 * Deletes a report on a given fanart made by a given user
	 * @param artID
	 * @param userID
	 * @return
	 */
	boolean deleteByArtIDAndUserID(int artID, int userID);
}
