package com.personal.revaturep2pokepostbe.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;
import com.personal.revaturep2pokepostbe.services.PokeCommService;

@RestController
@RequestMapping("/pokemon/comment")
public class PokeCommController {
	private final PokeCommService pokeCommServ;

	public PokeCommController(PokeCommService pokeCommServ) {
		this.pokeCommServ = pokeCommServ;
	}

	@PostMapping
	public ResponseEntity<PokeComment> postPokeComment(@RequestBody PokeComment newPokeComm) {
		PokeComment result = pokeCommServ.postPokeComment(newPokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/{commentID}")
	public ResponseEntity<Boolean> deletePokeComment(@PathVariable int commentID) {
		boolean result = pokeCommServ.deletePokeComment(commentID);
		return ResponseEntity.ok(result);
	}

	@PostMapping(path = "/rate")
	public ResponseEntity<RatePokeComm> ratePokeComment(@RequestBody RatePokeComm newRatePokeComm) {
		RatePokeComm result = pokeCommServ.ratePokeComment(newRatePokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unrate")
	public ResponseEntity<Boolean> unratePokeComment(@RequestBody Map<String, String> body) {
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = pokeCommServ.unratePokeComment(commentID, userID);
		return ResponseEntity.ok(result);
	}

	@PostMapping(path = "/report")
	public ResponseEntity<ReportPokeComm> reportPokeComment(ReportPokeComm newReportPokeComm) {
		ReportPokeComm result = pokeCommServ.reportPokeComment(newReportPokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unreport")
	public ResponseEntity<Boolean> unreportPokeComment(@RequestBody Map<String, String> body) {
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = pokeCommServ.unreportPokeComment(commentID, userID);
		return ResponseEntity.ok(result);
	}
}
