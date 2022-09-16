package com.personal.revaturep2pokepostbe.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Wishlist;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.repositories.WishlistRepository;

/**
 * A service class for the manipulation of wishlists
 * @author Barry Norton
 *
 */
@Service
public class WishlistService implements WishlistInterface{
	private final WishlistRepository wishRepo;
	
	public WishlistService(WishlistRepository wishRepo) {
		this.wishRepo = wishRepo;		
	}

	@Override
	public Wishlist addToWishlist(Wishlist newWish) throws SaveFailedException {
		try {
			return wishRepo.save(newWish);
		} catch (Exception e) {
			throw new SaveFailedException();
		}
	}

	@Override
	public boolean deleteFromWishlist(int wishID) throws RecordNotFoundException {
		try {
			wishRepo.deleteById(wishID);
			return true;
		} catch (Exception e) {
			throw new RecordNotFoundException("Wishlist", wishID);
		}
	}

	@Override
	public Page<Wishlist> getWishlistByUser(int userID, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		return wishRepo.findByUserId(new UserIDDTO(userID), pageParams);
	}

}
