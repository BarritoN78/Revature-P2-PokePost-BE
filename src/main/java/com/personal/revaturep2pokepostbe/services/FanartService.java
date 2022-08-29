package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;

public class FanartService implements FanartInterface{

	@Override
	public Fanart postFanart(Fanart newFanart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteFanart(int artID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fanart getFanartByID(int artID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Fanart> getAllFanart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RateFanart rateFanart(RateFanart newRateArtComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unrateFanart(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportFanart reportFanart(ReportFanart newReportArtComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override 
	public boolean unreportFanart(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

}
