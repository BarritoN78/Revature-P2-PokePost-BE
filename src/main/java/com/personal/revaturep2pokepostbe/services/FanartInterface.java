package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;

/**
 * An interface to be followed by FanartService
 * @author Barry Norton
 *
 */
public interface FanartInterface {
	/**
	 * Saves a fanart to the database
	 * @param rateFanart
	 * @return
	 */
	public Fanart postFanart(Fanart newFanart);
	
	/**
	 * Deletes the fanart with the given fanart ID
	 * @param artID
	 * @return
	 */
	public boolean deleteFanart(int artID);
	
	/**
	 * Retrieves a fanart with the given fanart ID
	 * @param artID
	 * @return
	 */
	public Fanart getFanartByID(int artID);
	
	/**
	 * Retrieves all fanart
	 * @return
	 */
	public List<Fanart> getAllFanart();

	/**
	 * Adds a user's rating of a fanart
	 * @param rateFanart
	 * @return
	 */
	public RateFanart rateFanart(RateFanart rateFanart);
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param artID
	 * @param userID
	 * @return
	 */
	public boolean unrateFanart(int artID, int userID);
	
	/**
	 * Adds a user's report of a fanart
	 * @param rateFanart
	 * @return
	 */
	public ReportFanart reportFanart(ReportFanart reportFanart);
	
	/**
	 * Deletes the report with a given fanart ID and user ID
	 * @param arttID
	 * @param userID
	 * @return
	 */
	public boolean unreportFanart(int artID, int userID);

}
