package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.repositories.ArtCommRepository;
import com.personal.revaturep2pokepostbe.repositories.RateArtCommRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportArtCommRepository;

/**
 * A service class for the manipulation of fanart comments
 * 
 * @author Barry Norton
 *
 */
@Service
public class ArtCommService implements ArtCommInterface {
	private final ArtCommRepository artCommRepo;
	private final RateArtCommRepository rateArtCommRepo;
	private final ReportArtCommRepository reportArtCommRepo;

	public ArtCommService(ArtCommRepository artCommRepo, RateArtCommRepository rateArtCommRepo,
			ReportArtCommRepository reportArtCommRepo) {
		this.artCommRepo = artCommRepo;
		this.rateArtCommRepo = rateArtCommRepo;
		this.reportArtCommRepo = reportArtCommRepo;
	}

	@Override
	public RateArtComm rateArtComment(RateArtComm rateArtComm) {
		return rateArtCommRepo.save(rateArtComm);
	}

	@Override
	public boolean unrateArtComment(int commentID, int userID) {
		return rateArtCommRepo.deleteByCommentIDAndUserID(commentID, userID);
	}

	@Override
	public ReportArtComm reportArtComment(ReportArtComm reportArtComm) {
		return reportArtCommRepo.save(reportArtComm);
	}

	@Override
	public boolean unreportArtComment(int commentID, int userID) {
		return reportArtCommRepo.deleteByCommentIDAndUserID(commentID, userID);
	}

	@Override
	public List<ArtComment> getAllArtComments(int artID) {
		return artCommRepo.findAll();
	}

	@Override
	public ArtComment postArtComment(ArtComment artComm) {
		return artCommRepo.save(artComm);
	}

	@Override
	public boolean deleteArtComment(int commentID) {
		try {
			artCommRepo.deleteById(commentID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
