package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;
import com.personal.revaturep2pokepostbe.repositories.PokeCommRepository;
import com.personal.revaturep2pokepostbe.repositories.RatePokeCommRepository;
import com.personal.revaturep2pokepostbe.repositories.ReportPokeCommRepository;

/**
 * A service class for the manipulation of pokemon comments
 * 
 * @author Barry Norton
 *
 */
@Service
public class PokeCommService implements PokeCommInterface {
	private final PokeCommRepository pokeCommRepo;
	private final RatePokeCommRepository ratePokeCommRepo;
	private final ReportPokeCommRepository reportPokeCommRepo;

	public PokeCommService(PokeCommRepository PokeCommRepo, RatePokeCommRepository ratePokeCommRepo,
			ReportPokeCommRepository reportPokeCommRepo) {
		this.pokeCommRepo = PokeCommRepo;
		this.ratePokeCommRepo = ratePokeCommRepo;
		this.reportPokeCommRepo = reportPokeCommRepo;
	}

	@Override
	public RatePokeComm ratePokeComment(RatePokeComm ratePokeComm) {
		return ratePokeCommRepo.save(ratePokeComm);
	}

	@Override
	public boolean unratePokeComment(int commentID, int userID) {
		return ratePokeCommRepo.deleteByCommentIDAndUserID(commentID, userID);
	}

	@Override
	public ReportPokeComm reportPokeComment(ReportPokeComm reportPokeComm) {
		return reportPokeCommRepo.save(reportPokeComm);
	}

	@Override
	public boolean unreportPokeComment(int commentID, int userID) {
		return reportPokeCommRepo.deleteByCommentIDAndUserID(commentID, userID);
	}

	@Override
	public List<PokeComment> getAllPokeComments(int PokeID) {
		return pokeCommRepo.findAll();
	}

	@Override
	public PokeComment postPokeComment(PokeComment PokeComm) {
		return pokeCommRepo.save(PokeComm);
	}

	@Override
	public boolean deletePokeComment(int commentID) {
		try {
			pokeCommRepo.deleteById(commentID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
