package com.personal.revaturep2pokepostbe.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.services.ArtCommService;

@RestController
@RequestMapping("/fanart/comment")
public class ArtCommController {
	private final ArtCommService artCommServ;
	
	public ArtCommController(ArtCommService artCommServ) {
		this.artCommServ = artCommServ;
	}
	
	@PostMapping
	public ResponseEntity<ArtComment> postArtComment(@RequestBody ArtComment newArtComm){
		ArtComment result = artCommServ.postArtComment(newArtComm);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(path = "/{commentID}")
	public ResponseEntity<String> deleteArtComment(@PathVariable int commentID) throws RecordNotFoundException {
		artCommServ.deleteArtComment(commentID);
		return ResponseEntity.ok("The record with the id of [" + commentID + "] has been deleted successfully!");
	}
	
	@PostMapping(path = "/rate")
	public ResponseEntity<RateArtComm> rateArtComment(@RequestBody RateArtComm newRateArtComm){
		RateArtComm result = artCommServ.rateArtComment(newRateArtComm);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(path = "/unrate")
	public ResponseEntity<Boolean> unrateArtComment(@RequestBody Map<String, String> body){
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = artCommServ.unrateArtComment(commentID, userID);
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(path = "/report")
	public ResponseEntity<ReportArtComm> reportArtComment(ReportArtComm newReportArtComm){
		ReportArtComm result = artCommServ.reportArtComment(newReportArtComm);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping(path = "/unreport")
	public ResponseEntity<Boolean> unreportArtComment(@RequestBody Map<String, String> body){
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = artCommServ.unreportArtComment(commentID, userID);
		return ResponseEntity.ok(result);
	}
}
