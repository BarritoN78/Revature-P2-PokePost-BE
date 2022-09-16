package com.personal.revaturep2pokepostbe.services;

import org.springframework.data.domain.Page;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyWishlistedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Wishlist;

/**
 * An interface to be followed by WishlistService
 * @author Barry Norton
 *
 */
public interface WishlistInterface {
	/**
	 * Saves a wish to a user's wishlist
	 * @param newWish
	 * @return
	 * @throws SaveFailedException 
	 * @throws AlreadyWishlistedException 
	 */
	public Wishlist addToWishlist(Wishlist newWish) throws SaveFailedException, AlreadyWishlistedException;
	
	/**
	 * Deletes a wish from a user's wishlist
	 * @param wishID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean deleteFromWishlist(int wishID) throws RecordNotFoundException;
	
	/**
	 * Retrieves a page of a user's wishlist
	 * @param userID
	 * @return
	 */
	public Page<Wishlist> getWishlistByUser(int userID, int page, int size);
}
