package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface RateFanartRepository extends JpaRepository<RateFanart, Integer>{

	/**
	 * Returns a boolean based on if  a rating on a given fanart made by a given user exists
	 * @param artID
	 * @param userID
	 * @return
	 */
	boolean existsByArtIDAndUserID(ArtIDDTO artID, UserIDDTO userID);
	
	/**
	 * Deletes a rating on a given fanart made by a given user
	 * @param artID
	 * @param userID
	 * @return
	 */
	int deleteByArtIDAndUserID(ArtIDDTO artID, UserIDDTO userID);

}
