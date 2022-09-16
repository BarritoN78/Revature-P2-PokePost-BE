package com.personal.revaturep2pokepostbe.services;

import org.hibernate.TransientPropertyValueException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;
import com.personal.revaturep2pokepostbe.models.dtos.PokeCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.PokemonIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
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
	public RatePokeComm ratePokeComment(RatePokeComm ratePokeComm)
			throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RatePokeComm result;
		PokeComment pokeCommRated;
		PokeCommIDDTO commentID = ratePokeComm.getCommentID();
		UserIDDTO userID = ratePokeComm.getUserID();
		if (!ratePokeCommRepo.existsByCommentIDAndUserID(commentID, userID)) {
			// Save the new rating
			try {
				result = ratePokeCommRepo.save(ratePokeComm);

				// Add a like to the respective fanart if the rating saved
				pokeCommRated = pokeCommRepo.findById(ratePokeComm.getCommentID().getId()).get();
				pokeCommRated.setLikes(pokeCommRated.getLikes() + 1);
				pokeCommRepo.save(pokeCommRated);
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
			return result;
		} else {
			throw new AlreadyRatedException("PokeComment", commentID.getId(), userID.getId());
		}
	}

	@Transactional
	@Override
	public boolean unratePokeComment(PokeCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException {
		PokeComment unratedPokeComm;
		try {
			if (ratePokeCommRepo.deleteByCommentIDAndUserID(commentID, userID) > 0) {
				// Remove a like from the respective fanart if the amount of likes is above zero
				unratedPokeComm = pokeCommRepo.findById(commentID.getId()).get();
				if (unratedPokeComm.getLikes() > 0) {
					unratedPokeComm.setLikes(unratedPokeComm.getLikes() - 1);
					pokeCommRepo.save(unratedPokeComm);
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
	public ReportPokeComm reportPokeComment(ReportPokeComm reportPokeComm)
			throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportPokeComm result;
		PokeComment pokeCommRated;
		PokeCommIDDTO commentID = reportPokeComm.getCommentID();
		UserIDDTO userID = reportPokeComm.getUserID();
		if (!reportPokeCommRepo.existsByCommentIDAndUserID(commentID, userID)) {
			// Save the new rating
			try {
				result = reportPokeCommRepo.save(reportPokeComm);

				// Add a like to the respective fanPoke if the rating saved
				pokeCommRated = pokeCommRepo.findById(reportPokeComm.getCommentID().getId()).get();
				pokeCommRated.setLikes(pokeCommRated.getLikes() + 1);
				pokeCommRepo.save(pokeCommRated);
			} catch (TransientPropertyValueException e) {
				throw new RecordNotFoundException();
			} catch (Exception e) {
				throw new SaveFailedException();
			}
			return result;
		} else {
			throw new AlreadyReportedException("PokeComment", commentID.getId(), userID.getId());
		}
	}

	@Transactional
	@Override
	public boolean unreportPokeComment(PokeCommIDDTO commentID, UserIDDTO userID) throws RecordNotFoundException {
		PokeComment unreportedPokeComm;
		try {
			if (reportPokeCommRepo.deleteByCommentIDAndUserID(commentID, userID) > 0) {
				// Remove a report from the respective fanPoke if the amount of Reports is above
				// zero
				unreportedPokeComm = pokeCommRepo.findById(commentID.getId()).get();
				if (unreportedPokeComm.getReports() > 0) {
					unreportedPokeComm.setReports(unreportedPokeComm.getReports() - 1);
					pokeCommRepo.save(unreportedPokeComm);
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
	public Page<PokeComment> getPokeCommentsByPokeId(int pokeID, int page, int size) {
		Pageable pageParams = PageRequest.of(page, size, Sort.by(Sort.Order.asc("id")));
		return pokeCommRepo.findByPokeId(new PokemonIDDTO(pokeID), pageParams);
	}

	@Override
	public PokeComment postPokeComment(PokeComment PokeComm) throws SaveFailedException {
		try {
			return pokeCommRepo.save(PokeComm);
		} catch (Exception e) {
			throw new SaveFailedException();
		}
	}

	@Override
	public boolean deletePokeComment(int commentID) throws RecordNotFoundException {
		try {
			pokeCommRepo.deleteById(commentID);
			return true;
		} catch (Exception e) {
			throw new RecordNotFoundException("PokeComment", commentID);
		}
	}

}
