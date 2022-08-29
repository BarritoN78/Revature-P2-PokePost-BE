package com.personal.revaturep2pokepostbe.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;

/**
 * A service class for the manipulation of pokemon comments
 * @author Barry Norton
 *
 */
@Service
public class PokeCommService implements PokeCommInterface{

	@Override
	public RatePokeComm ratePokeComment(RatePokeComm ratePokeComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unratePokeComment(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReportPokeComm reportPokeComment(ReportPokeComm reportPokeComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean unreportPokeComment(int commentID, int userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<PokeComment> getAllPokeComments(int pokemonID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PokeComment postPokeComment(PokeComment pokeComm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePokeComment(int commentID) {
		// TODO Auto-generated method stub
		return false;
	}

}
