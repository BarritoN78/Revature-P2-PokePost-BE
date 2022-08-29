package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
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
	public boolean deleteFanart(int artID) {
		try {
			artRepo.deleteById(artID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Fanart getFanartByID(int artID) {
		try {
			return artRepo.findById(artID).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Fanart> getAllFanart() {
		return artRepo.findAll();
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
