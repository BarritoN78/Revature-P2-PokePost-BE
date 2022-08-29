package com.personal.revaturep2pokepostbe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.models.Wishlist;
import com.personal.revaturep2pokepostbe.services.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	private final WishlistService wishServ;
	
	public WishlistController(WishlistService wishServ) {
		this.wishServ = wishServ;
	}
	
	@PostMapping
	public ResponseEntity<Wishlist> addToWishlist(@RequestBody Wishlist newWish){
		Wishlist result = wishServ.addToWishlist(newWish);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{wishID}")
	public ResponseEntity<Boolean> deleteFromWishlist(@PathVariable int wishID){
		boolean result = wishServ.deleteFromWishlist(wishID);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{userID}")
	public ResponseEntity<List<Wishlist>> getWishlistByUser(@PathVariable int userID){
		List<Wishlist> result = wishServ.getWishlistByUser(userID);
		return ResponseEntity.ok(result);
	}
}
