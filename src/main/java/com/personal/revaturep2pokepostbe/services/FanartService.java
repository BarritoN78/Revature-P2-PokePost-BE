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

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.repositories.FanartRepository;
import com.personal.revaturep2pokepostbe.repositories.RateFanartRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportFanartRepository;

/**
 * A service class for the manipulation of fanart
 * 
 * @author Barry Norton
 *
 */
@Service
public class FanartService implements FanartInterface {
	private final FanartRepository artRepo;
	private final RateFanartRepository rateArtRepo;
	private final ReportFanartRepository reportArtRepo;

	public FanartService(FanartRepository artRepo, RateFanartRepository rateArtRepo,
			ReportFanartRepository reportArtRepo) {
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
		Page<Fanart> result = artRepo.findByTitleContainsIgnoreCase(title, pageParams);
		List<ArtDTO> resultDTO = new ArrayList<ArtDTO>();

		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public Page<ArtDTO> getFanartByTags(String tags, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		Page<Fanart> result = artRepo.findByTagsContainsIgnoreCase(tags, pageParams);
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
			result = artRepo.findByPostDateGreaterThanEqual(Date.valueOf(postDate), pageParams);
		} else {
			result = artRepo.findByPostDateLessThanEqual(Date.valueOf(postDate), pageParams);
		}

		for (Fanart art : result) {
			resultDTO.add(new ArtDTO(art));
		}

		Page<ArtDTO> artPage = new PageImpl<ArtDTO>(resultDTO);
		return artPage;
	}

	@Override
	public RateFanart rateFanart(RateFanart newRateArt) throws AlreadyRatedException {
		ArtIDDTO artID = newRateArt.getArtID();
		UserIDDTO userID = newRateArt.getUserID();
		if (!rateArtRepo.existsByArtIDAndUserID(artID, userID)) {
			return rateArtRepo.save(newRateArt);
		} else {
			throw new AlreadyRatedException("Fanart", artID.getId(), userID.getId());
		}
	}

	@Override
	public boolean unrateFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException {
		try {
			return rateArtRepo.deleteByArtIDAndUserID(artID, userID);
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public ReportFanart reportFanart(ReportFanart newReportArt) throws AlreadyReportedException {
		int artID = newReportArt.getArtID().getId();
		int userID = newReportArt.getArtID().getId();
		if (!reportArtRepo.existsByArtIDAndUserID(artID, userID)) {
			return reportArtRepo.save(newReportArt);
		} else {
			throw new AlreadyReportedException("Fanart", artID, userID);
		}
	}

	@Override
	public boolean unreportFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException {
		try {
			return reportArtRepo.deleteByArtIDAndUserID(artID, userID);
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

}
