package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;

/**
 * A service class for the manipulation of fanart comments
 * @author Barry Norton
 *
 */
public class ArtCommService implements ArtCommInterface{

	@Override
	public RateArtComm rateArtComment(RateArtComm rateArtComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unrateArtComment(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportArtComm reportArtComment(ReportArtComm reportArtComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unreportArtComment(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ArtComment> getAllArtComments(int artID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArtComment postArtComment(ArtComment artComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteArtComment(int commentID) {
		// TODO Auto-generated method stub
		return false;
	}

}
