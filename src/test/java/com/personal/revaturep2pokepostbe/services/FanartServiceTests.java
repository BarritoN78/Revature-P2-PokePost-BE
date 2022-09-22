package com.personal.revaturep2pokepostbe.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.TransientPropertyValueException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.revaturep2pokepostbe.RevatureP2PokepostBeApplication;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.repositories.FanartRepository;
import com.personal.revaturep2pokepostbe.repositories.RateFanartRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportFanartRepository;

@SpringBootTest(classes=RevatureP2PokepostBeApplication.class)
public class FanartServiceTests implements FanartInterface {
	private final FanartRepository artRepo;
	private final RateFanartRepository rateArtRepo;
	private final ReportFanartRepository reportArtRepo;

	public FanartServiceTests(FanartRepository artRepo, RateFanartRepository rateArtRepo,
			ReportFanartRepository reportArtRepo) {
		this.artRepo = artRepo;
		this.rateArtRepo = rateArtRepo;
		this.reportArtRepo = reportArtRepo;

	}

	@Override
	public Fanart postFanart(Fanart newFanart) throws SaveFailedException {
		try {
			return artRepo.save(newFanart);
		} catch (Exception e) {
			throw new SaveFailedException();
		}
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
	public RateFanart rateFanart(RateFanart newRateArt) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RateFanart result;
		Fanart ratedArt;
		ArtIDDTO artID = newRateArt.getArtID();
		UserIDDTO userID = newRateArt.getUserID();
		if (!rateArtRepo.existsByArtIDAndUserID(artID, userID)) {
			// Save the new rating
			try {
				result = rateArtRepo.save(newRateArt);				

				// Add a like to the respective fanart if the rating saved
				ratedArt = artRepo.findById(newRateArt.getArtID().getId()).get();
				ratedArt.setLikes(ratedArt.getLikes() + 1);
				artRepo.save(ratedArt);
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
			return result;
		} else {
			throw new AlreadyRatedException("Fanart", artID.getId(), userID.getId());
		}
	}

	@Override
	@Transactional
	public boolean unrateFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException {
		Fanart unratedArt;
		try {
			if (rateArtRepo.deleteByArtIDAndUserID(artID, userID) > 0) {
				// Remove a like from the respective fanart if the amount of likes is above zero
				unratedArt = artRepo.findById(artID.getId()).get();
				if (unratedArt.getLikes() > 0) {
					unratedArt.setLikes(unratedArt.getLikes() - 1);
					artRepo.save(unratedArt);
				}
				return true;
			} else {
				throw new RecordNotFoundException();
			}
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

	@Override
	public ReportFanart reportFanart(ReportFanart newReportArt)
			throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportFanart result;
		Fanart reportedArt;
		ArtIDDTO artID = newReportArt.getArtID();
		UserIDDTO userID = newReportArt.getUserID();
		if (!reportArtRepo.existsByArtIDAndUserID(artID, userID)) {
			try {
				result = reportArtRepo.save(newReportArt);

				// Add a report to the respective fanart if the report saved
				reportedArt = artRepo.findById(newReportArt.getArtID().getId()).get();
				reportedArt.setReports(reportedArt.getReports() + 1);
				artRepo.save(reportedArt);
				return result;
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
		} else {
			throw new AlreadyReportedException("Fanart", artID.getId(), userID.getId());
		}
	}

	@Override
	@Transactional
	public boolean unreportFanart(ArtIDDTO artID, UserIDDTO userID) throws RecordNotFoundException {
		Fanart unreportedArt;
		try {
			if (reportArtRepo.deleteByArtIDAndUserID(artID, userID) > 0) {
				// Remove a report from the respective fanart if the amount of Reports is above
				// zero
				unreportedArt = artRepo.findById(artID.getId()).get();
				if (unreportedArt.getReports() > 0) {
					unreportedArt.setReports(unreportedArt.getReports() - 1);
					artRepo.save(unreportedArt);
				}
				return true;
			} else {
				throw new RecordNotFoundException();
			}
		} catch (Exception e) {
			throw new RecordNotFoundException();
		}
	}

}
