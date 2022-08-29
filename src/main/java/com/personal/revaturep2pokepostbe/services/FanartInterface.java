package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.Fanart;

/**
 * An interface to be followed by FanartService
 * @author Barry Norton
 *
 */
public interface FanartInterface {
	/**
	 * Saves a fanart to the database
	 * @param rateArtComm
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

}
