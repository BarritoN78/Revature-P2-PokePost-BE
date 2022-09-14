package com.personal.revaturep2pokepostbe.services;

import org.springframework.data.domain.Page;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

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
	 * @throws SaveFailedException 
	 */
	public Fanart postFanart(Fanart newFanart) throws SaveFailedException;
	
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
	public Page<ArtDTO> getFanartByTags(String tags, int page, int size);
	
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
	 * @throws AlreadyRatedException 
	 * @throws RecordNotFoundException 
	 * @throws SaveFailedException 
	 */
	public RateFanart rateFanart(RateFanart rateFanart) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException;
	
	/**
	 * Deletes the rating with a given comment ID and user ID
	 * @param artID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unrateFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException;
	
	/**
	 * Adds a user's report of a fanart
	 * @param rateFanart
	 * @return
	 * @throws AlreadyReportedException 
	 * @throws RecordNotFoundException 
	 * @throws SaveFailedException 
	 */
	public ReportFanart reportFanart(ReportFanart reportFanart) throws AlreadyReportedException, RecordNotFoundException, SaveFailedException;
	
	/**
	 * Deletes the report with a given fanart ID and user ID
	 * @param arttID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean unreportFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException;

}
