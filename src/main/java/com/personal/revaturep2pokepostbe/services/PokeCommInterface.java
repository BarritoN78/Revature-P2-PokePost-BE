package com.personal.revaturep2pokepostbe.services;

import org.springframework.data.domain.Page;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;
import com.personal.revaturep2pokepostbe.models.dtos.PokeCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

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
	 * @throws SaveFailedException 
	 * @throws RecordNotFoundException 
	 * @throws AlreadyRatedException 
	 */
	public RatePokeComm ratePokeComment(RatePokeComm ratePokeComm) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException;
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unratePokeComment(PokeCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException;
	
	/**
	 * Adds a user's report of a pokemon comment
	 * @param ratePokeComm
	 * @return
	 * @throws SaveFailedException 
	 * @throws RecordNotFoundException
	 * @throws AlreadyReportedException 
	 */
	public ReportPokeComm reportPokeComment(ReportPokeComm reportPokeComm) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException, AlreadyReportedException;
	
	/**
	 * Deletes the report with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unreportPokeComment(PokeCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException;
	
	/**
	 * Returns a page of comments associated with a given pokemon
	 * @param pokemonID
	 * @param pageReq
	 * @return
	 */
	public Page<PokeComment> getPokeCommentsByPokeId(int pokemonID, int page, int size);
	
	/**
	 * Saves or updates a pokemon comment
	 * @param pokeComm
	 * @return
	 * @throws SaveFailedException 
	 */
	public PokeComment postPokeComment(PokeComment pokeComm) throws SaveFailedException;
	
	/**
	 * Deletes the pokemon comment with the given comment ID
	 * @param commentID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean deletePokeComment(int commentID) throws RecordNotFoundException;

}
