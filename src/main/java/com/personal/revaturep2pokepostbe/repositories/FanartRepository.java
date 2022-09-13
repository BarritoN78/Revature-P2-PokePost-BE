package com.personal.revaturep2pokepostbe.repositories;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.Fanart;

@Repository
public interface FanartRepository extends JpaRepository<Fanart, Integer>{

	/**
	 * Returns a page of fanart results
	 * @param pageReq
	 */
	public Page<Fanart> findAll(Pageable pageReq);
	
	/**
	 * Returns a page of fanart results with a given title
	 * @param title
	 * @param pageReq
	 */
	public Page<Fanart> findByTitleContainsIgnoreCase(String title, Pageable pageReq);
	
	/**
	 * Returns a page of fanart results with the given tags
	 * @param tags
	 * @param pageReq
	 */
	public Page<Fanart> findByTagsContainsIgnoreCase(String tags, Pageable pageReq);

	/**
	 * Returns a page of fanart results by the given author
	 * @param postDate
	 * @param pageReq
	 */
	public Page<Fanart> findByPostDateGreaterThanEqual(Date postDate, Pageable pageReq);

	/**
	 * Returns a page of fanart results by the given author
	 * @param postDate
	 * @param pageReq
	 */
	public Page<Fanart> findByPostDateLessThanEqual(Date postDate, Pageable pageReq);
}
