package com.personal.revaturep2pokepostbe.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.repositories.FanartRepository;
import com.personal.revaturep2pokepostbe.repositories.RateFanartRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportFanartRepository;

/**
 * A service class for the manipulation of fanart
 * @author Barry Norton
 *
 */
@Service
public class FanartService implements FanartInterface{
	private final FanartRepository artRepo;
	private final RateFanartRepository rateArtRepo;
	private final ReportFanartRepository reportArtRepo;
	
	public FanartService(FanartRepository artRepo, RateFanartRepository rateArtRepo, ReportFanartRepository reportArtRepo) {
		this.artRepo = artRepo;
		this.rateArtRepo = rateArtRepo;
		this.reportArtRepo = reportArtRepo;
		
	}

	@Override
	public Fanart postFanart(Fanart newFanart) {
		return artRepo.save(newFanart);
	}

	@Override
	public boolean deleteFanart(int artID) throws RecordNotFoundException {
		try {
			artRepo.deleteById(artID);
			return true;
		} catch (Exception e) {
			throw new RecordNotFoundException("Fanart", artID);
		}
	}

	@Override
	public ArtDTO getFanartByID(int artID) throws RecordNotFoundException {
		try {
			return new ArtDTO(artRepo.findById(artID).get());
		} catch (Exception e) {
			throw new RecordNotFoundException("Fanart", artID);
		}
	}

	@Override
	public Page<ArtDTO> getAllFanartByPage(int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		Page<Fanart> result = artRepo.findAll(pageParams);
		List<ArtDTO> resultDTO = new ArrayList<ArtDTO>();
		
		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public Page<ArtDTO> getFanartByTitle(String title, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		Page<Fanart> result = artRepo.findByTitleContains(title, pageParams);
		List<ArtDTO> resultDTO = new ArrayList<ArtDTO>();
		
		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public Page<ArtDTO> gerFanartByTags(String tags, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		Page<Fanart> result = artRepo.findByTagsContains(tags, pageParams);
		List<ArtDTO> resultDTO = new ArrayList<ArtDTO>();
		
		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public Page<ArtDTO> getFanartByPostDate(String postDate, Boolean greaterThan, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		Page<Fanart> result;
		List<ArtDTO> resultDTO = new ArrayList<ArtDTO>();
		if (greaterThan) {
			result = artRepo.findByPostDateGreaterThanEqualTo(Date.valueOf(postDate), pageParams);
		} else {
			result = artRepo.findByPostDateLessThanEqualTo(Date.valueOf(postDate), pageParams);
		}
		
		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public RateFanart rateFanart(RateFanart newRateArtComm) {
		return rateArtRepo.save(newRateArtComm);
	}

	@Override
	public boolean unrateFanart(int artID, int userID) {
		return rateArtRepo.deleteByArtIDAndUserID(artID, userID);
	}

	@Override
	public ReportFanart reportFanart(ReportFanart newReportArtComm) {
		return reportArtRepo.save(newReportArtComm);
	}

	@Override 
	public boolean unreportFanart(int artID, int userID) {
		return reportArtRepo.deleteByArtIDAndUserID(artID, userID);
	}

}
