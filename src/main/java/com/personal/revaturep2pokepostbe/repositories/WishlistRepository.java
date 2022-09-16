package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.Wishlist;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Integer>{

	/**
	 * Retrieves a page of a user's wishlist
	 * @param userIDDTO
	 * @param pageParams
	 * @return
	 */
	Page<Wishlist> findByUserId(UserIDDTO userIDDTO, Pageable pageParams);

}
