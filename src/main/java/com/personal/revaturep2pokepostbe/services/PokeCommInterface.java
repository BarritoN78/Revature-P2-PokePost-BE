package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;

/**
 * An interface to be followed by PokeCommService
 * @author Barry Norton
 *
 */
public interface PokeCommInterface {
	/**
	 * Adds a user's rating of a pokemon comment
	 * @param ratePokeComm
	 * @return
	 */
	public RatePokeComm ratePokeComment(RatePokeComm ratePokeComm);
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 */
	public boolean unratePokeComment(int commentID, int userID);
	
	/**
	 * Adds a user's report of a pokemon comment
	 * @param ratePokeComm
	 * @return
	 */
	public ReportPokeComm reportPokeComment(ReportPokeComm reportPokeComm);
	
	/**
	 * Deletes the report with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 */
	public boolean unreportPokeComment(int commentID, int userID);
	
	/**
	 * Returns all comments associated with a given fanPoke ID
	 * @param pokemonID
	 * @return
	 */
	public List<PokeComment> getAllPokeComments(int pokemonID);
	
	/**
	 * Saves or updates a pokemon comment
	 * @param pokeComm
	 * @return
	 */
	public PokeComment postPokeComment(PokeComment pokeComm);
	
	/**
	 * Deletes the pokemon comment with the given comment ID
	 * @param commentID
	 * @return
	 */
	public boolean deletePokeComment(int commentID);

}
