package com.personal.revaturep2pokepostbe.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyWishlistedException;
import com.personal.revaturep2pokepostbe.exceptions.PageNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Wishlist;
import com.personal.revaturep2pokepostbe.services.WishlistService;


@WebMvcTest(controllers = WishlistController.class)
public class WishlistControllerTests {
	private final WishlistService wishServ;

	public WishlistControllerTests(WishlistService wishServ) {
		this.wishServ = wishServ;
	}

	@PostMapping
	public ResponseEntity<Wishlist> addToWishlist(@RequestBody Wishlist newWish) throws SaveFailedException, AlreadyWishlistedException {
		Wishlist result = wishServ.addToWishlist(newWish);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{wishID}")
	public ResponseEntity<String> deleteFromWishlist(@PathVariable int wishID) throws RecordNotFoundException {
		wishServ.deleteFromWishlist(wishID);
		return ResponseEntity.ok("The record with the id of [" + wishID + "] has been deleted successfully!");
	}

	@GetMapping("/{userID}")
	public ResponseEntity<Page<Wishlist>> getWishlistByUser(@PathVariable int userID, @RequestParam int page,
			@RequestParam int size) throws PageNotFoundException {
		try {
			Page<Wishlist> result = wishServ.getWishlistByUser(userID, page, size);
			if (result.hasContent()) {
				return ResponseEntity.ok(result);
			} else {
				throw new PageNotFoundException(page);
			}
		} catch (IllegalArgumentException e) {
			throw new PageNotFoundException(page);
		}
	}
}
