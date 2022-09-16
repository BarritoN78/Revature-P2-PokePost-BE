package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;

@Repository
public interface ArtCommRepository extends JpaRepository<ArtComment, Integer> {

	/**
	 * Retrieves a page of comments associated with a given fanart
	 * @param artId
	 * @param pageReq
	 * @return
	 */
	Page<ArtComment> findByArtId(ArtIDDTO artId, Pageable pageReq);
}
