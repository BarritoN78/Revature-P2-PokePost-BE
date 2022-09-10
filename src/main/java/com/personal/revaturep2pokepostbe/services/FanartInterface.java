package com.personal.revaturep2pokepostbe.services;

import org.springframework.data.domain.Page;

import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;

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
	 * @throws RecordNotFoundException 
	 */
	public boolean deleteFanart(int artID) throws RecordNotFoundException;
	
	/**
	 * Retrieves a fanart with the given fanart ID
	 * @param artID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public ArtDTO getFanartByID(int artID) throws RecordNotFoundException;
	
	/**
	 * Retrieves all fanart and returns a page of the results
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ArtDTO> getAllFanartByPage(int page, int size);
	
	/**
	 * Retrieves fanart with a given title and returns a page of the results
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ArtDTO> getFanartByTitle(String title, int page, int size);
	
	/**
	 * Retrieves fanart with the given tags and returns a page of the results
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ArtDTO> gerFanartByTags(String tags, int page, int size);
	
	/**
	 * Retrieves fanart posted before or after a given date and returns a page of the results
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<ArtDTO> getFanartByPostDate(String postDate, Boolean greaterThan, int page, int size);

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
