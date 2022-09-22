package com.personal.revaturep2pokepostbe.services;

import org.hibernate.TransientPropertyValueException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
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
import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.repositories.ArtCommRepository;
import com.personal.revaturep2pokepostbe.repositories.RateArtCommRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportArtCommRepository;

@SpringBootTest(classes=RevatureP2PokepostBeApplication.class)
public class ArtCommServiceTests implements ArtCommInterface {
	private final ArtCommRepository artCommRepo;
	private final RateArtCommRepository rateArtCommRepo;
	private final ReportArtCommRepository reportArtCommRepo;

	public ArtCommServiceTests(ArtCommRepository artCommRepo, RateArtCommRepository rateArtCommRepo,
			ReportArtCommRepository reportArtCommRepo) {
		this.artCommRepo = artCommRepo;
		this.rateArtCommRepo = rateArtCommRepo;
		this.reportArtCommRepo = reportArtCommRepo;
	}

	@Override
	public RateArtComm rateArtComment(RateArtComm rateArtComm)
			throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RateArtComm result;
		ArtComment artCommRated;
		ArtCommIDDTO commentID = rateArtComm.getCommentID();
		UserIDDTO userID = rateArtComm.getUserID();
		if (!rateArtCommRepo.existsByCommentIDAndUserID(commentID, userID)) {
			// Save the new rating
			try {
				result = rateArtCommRepo.save(rateArtComm);

				// Add a like to the respective fanart if the rating saved
				artCommRated = artCommRepo.findById(rateArtComm.getCommentID().getId()).get();
				artCommRated.setLikes(artCommRated.getLikes() + 1);
				artCommRepo.save(artCommRated);
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
			return result;
		} else {
			throw new AlreadyRatedException("ArtComment", commentID.getId(), userID.getId());
		}
	}

	@Transactional
	@Override
	public boolean unrateArtComment(ArtCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException {
		ArtComment unratedArtComm;
		try {
			if (rateArtCommRepo.deleteByCommentIDAndUserID(commentID, userID) > 0) {
				// Remove a like from the respective fanart if the amount of likes is above zero
				unratedArtComm = artCommRepo.findById(commentID.getId()).get();
				if (unratedArtComm.getLikes() > 0) {
					unratedArtComm.setLikes(unratedArtComm.getLikes() - 1);
					artCommRepo.save(unratedArtComm);
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
	public ReportArtComm reportArtComment(ReportArtComm reportArtComm) throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportArtComm result;
		ArtComment artCommRated;
		ArtCommIDDTO commentID = reportArtComm.getCommentID();
		UserIDDTO userID = reportArtComm.getUserID();
		if (!reportArtCommRepo.existsByCommentIDAndUserID(commentID, userID)) {
			// Save the new rating
			try {
				result = reportArtCommRepo.save(reportArtComm);

				// Add a like to the respective fanart if the rating saved
				artCommRated = artCommRepo.findById(reportArtComm.getCommentID().getId()).get();
				artCommRated.setLikes(artCommRated.getLikes() + 1);
				artCommRepo.save(artCommRated);
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
			return result;
		} else {
			throw new AlreadyReportedException("ArtComment", commentID.getId(), userID.getId());
		}
	}

	@Transactional
	@Override
	public boolean unreportArtComment(ArtCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException {
		ArtComment unreportedArt;
		try {
			if (reportArtCommRepo.deleteByCommentIDAndUserID(commentID, userID) > 0) {
				// Remove a report from the respective fanart if the amount of Reports is above
				// zero
				unreportedArt = artCommRepo.findById(commentID.getId()).get();
				if (unreportedArt.getReports() > 0) {
					unreportedArt.setReports(unreportedArt.getReports() - 1);
					artCommRepo.save(unreportedArt);
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
	public Page<ArtComment> getArtCommentsByArtID(int artID, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		return artCommRepo.findByArtId(new ArtIDDTO(artID),pageParams);
	}

	@Override
	public ArtComment postArtComment(ArtComment artComm) throws SaveFailedException {
		try {
			return artCommRepo.save(artComm);
		} catch (Exception e) {
			throw new SaveFailedException();
		}
	}

	@Override
	public boolean deleteArtComment(int commentID) throws RecordNotFoundException {
		try {
			artCommRepo.deleteById(commentID);
			return true;
		} catch (Exception e) {
			throw new RecordNotFoundException("ArtComment", commentID);
		}
	}

}
