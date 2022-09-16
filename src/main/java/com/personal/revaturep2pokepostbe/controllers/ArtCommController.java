package com.personal.revaturep2pokepostbe.controllers;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.PageNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.services.ArtCommService;

@RestController
@RequestMapping("/fanart/comment")
public class ArtCommController {
	private final ArtCommService artCommServ;

	public ArtCommController(ArtCommService artCommServ) {
		this.artCommServ = artCommServ;
	}

	@GetMapping(path = "/{artId}")
	public ResponseEntity<Page<ArtComment>> getArtCommentsByArtId(@PathVariable int artId, @RequestParam int page,
			@RequestParam int size) throws PageNotFoundException {
		try {
			Page<ArtComment> result = artCommServ.getArtCommentsByArtID(artId, page, size);
			if (result.hasContent()) {
				return ResponseEntity.ok(result);
			} else {
				throw new PageNotFoundException(page);
			}
		} catch (IllegalArgumentException e) {
			throw new PageNotFoundException(page);
		}
	}

	@PostMapping
	public ResponseEntity<ArtComment> postArtComment(@RequestBody ArtComment newArtComm) throws SaveFailedException {
		ArtComment result = artCommServ.postArtComment(newArtComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/{commentID}")
	public ResponseEntity<String> deleteArtComment(@PathVariable int commentID) throws RecordNotFoundException {
		artCommServ.deleteArtComment(commentID);
		return ResponseEntity.ok("The record with the id of [" + commentID + "] has been deleted successfully!");
	}

	@PostMapping(path = "/rate")
	public ResponseEntity<RateArtComm> rateArtComment(@RequestBody RateArtComm newRateArtComm)
			throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RateArtComm result = artCommServ.rateArtComment(newRateArtComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unrate")
	public ResponseEntity<String> unrateArtComment(@RequestBody Map<String, String> body)
			throws RecordNotFoundException {
		try {
			int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			artCommServ.unrateArtComment(new ArtCommIDDTO(commentID), new UserIDDTO(userID));
			return ResponseEntity.ok("User[" + userID + "] has unrated ArtComment[" + commentID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}

	@PostMapping(path = "/report")
	public ResponseEntity<ReportArtComm> reportArtComment(@RequestBody ReportArtComm newReportArtComm)
			throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportArtComm result = artCommServ.reportArtComment(newReportArtComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unreport")
	public ResponseEntity<String> unreportArtComment(@RequestBody Map<String, String> body)
			throws RecordNotFoundException {
		try {
			int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			artCommServ.unreportArtComment(new ArtCommIDDTO(commentID), new UserIDDTO(userID));
			return ResponseEntity.ok("User[" + userID + "] has unrated ArtComment[" + commentID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}
}
