package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

/**
 * An interface to be followed by ArtCommService
 * @author Barry Norton
 *
 */
public interface ArtCommInterface {
	/**
	 * Adds a user's rating of a fanart comment
	 * @param rateArtComm
	 * @return
	 * @throws AlreadyRatedException 
	 * @throws RecordNotFoundException 
	 * @throws SaveFailedException 
	 */
	public RateArtComm rateArtComment(RateArtComm rateArtComm) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException;
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unrateArtComment(ArtCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException;
	
	/**
	 * Adds a user's report of a fanart comment
	 * @param rateArtComm
	 * @return
	 * @throws AlreadyReportedException 
	 * @throws RecordNotFoundException 
	 * @throws SaveFailedException 
	 */
	public ReportArtComm reportArtComment(ReportArtComm reportArtComm) throws AlreadyReportedException, RecordNotFoundException, SaveFailedException;
	
	/**
	 * Deletes the report with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unreportArtComment(ArtCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException;
	
	/**
	 * Returns all comments associated with a given fanart ID
	 * @param artID
	 * @return
	 */
	public List<ArtComment> getAllArtComments(int artID);
	
	/**
	 * Saves or updates a fanart comment
	 * @param artComm
	 * @return
	 * @throws SaveFailedException 
	 */
	public ArtComment postArtComment(ArtComment artComm) throws SaveFailedException;
	
	/**
	 * Deletes the fanart comment with the given comment ID
	 * @param commentID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean deleteArtComment(int commentID) throws RecordNotFoundException;
}
