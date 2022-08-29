package com.personal.revaturep2pokepostbe.services;

import java.util.List;

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
	 */
	public Wishlist addToWishlist(Wishlist newWish);
	
	/**
	 * Deletes a wish from a user's wishlist
	 * @param wishID
	 * @return
	 */
	public boolean deleteFromWishlist(int wishID);
	
	/**
	 * Retrieves a user's wishlist
	 * @param userID
	 * @return
	 */
	public List<Wishlist> getWishlistByUser(int userID);
}
