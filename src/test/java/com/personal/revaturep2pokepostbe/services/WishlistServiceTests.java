package com.personal.revaturep2pokepostbe.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.RevatureP2PokepostBeApplication;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyWishlistedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Wishlist;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.repositories.WishlistRepository;

@SpringBootTest(classes=RevatureP2PokepostBeApplication.class)
public class WishlistServiceTests implements WishlistInterface {
	private final WishlistRepository wishRepo;

	public WishlistServiceTests(WishlistRepository wishRepo) {
		this.wishRepo = wishRepo;
	}

	@Override
	public Wishlist addToWishlist(Wishlist newWish) throws SaveFailedException, AlreadyWishlistedException {
		if (!wishRepo.existsByPokeIdAndUserId(newWish.getPokeId(), newWish.getUserId())) {
			try {
				return wishRepo.save(newWish);
			} catch (Exception e) {
				throw new SaveFailedException();
			}
		} else {
			throw new AlreadyWishlistedException();
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
