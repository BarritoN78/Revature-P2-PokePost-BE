package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;

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
	 */
	public RateArtComm rateArtComment(RateArtComm rateArtComm);
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 */
	public boolean unrateArtComment(int commentID, int userID);
	
	/**
	 * Adds a user's report of a fanart comment
	 * @param rateArtComm
	 * @return
	 */
	public ReportArtComm reportArtComment(ReportArtComm reportArtComm);
	
	/**
	 * Deletes the report with a given comment ID and user ID
	 * @param commentID
	 * @param userID
	 * @return
	 */
	public boolean unreportArtComment(int commentID, int userID);
	
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
	 */
	public ArtComment postArtComment(ArtComment artComm);
	
	/**
	 * Deletes the fanart comment with the given comment ID
	 * @param commentID
	 * @return
	 */
	public boolean deleteArtComment(int commentID);
}
