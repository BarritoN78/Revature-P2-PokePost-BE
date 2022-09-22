package com.personal.revaturep2pokepostbe.controllers;

import java.util.Map;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
import com.personal.revaturep2pokepostbe.models.PokeComment;
import com.personal.revaturep2pokepostbe.models.RatePokeComm;
import com.personal.revaturep2pokepostbe.models.ReportPokeComm;
import com.personal.revaturep2pokepostbe.models.dtos.PokeCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.services.PokeCommService;


@WebMvcTest
public class PokeCommControllerTests {
	private final PokeCommService pokeCommServ;

	public PokeCommControllerTests(PokeCommService pokeCommServ) {
		this.pokeCommServ = pokeCommServ;
	}

	@GetMapping(path = "/{pokeId}")
	public ResponseEntity<Page<PokeComment>> getPokeCommentsByPokeId(@PathVariable int pokeId, @RequestParam int page,
			@RequestParam int size) throws PageNotFoundException {
		try {
			Page<PokeComment> result = pokeCommServ.getPokeCommentsByPokeId(pokeId, page, size);
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
	public ResponseEntity<PokeComment> postPokeComment(@RequestBody PokeComment newPokeComm)
			throws SaveFailedException {
		PokeComment result = pokeCommServ.postPokeComment(newPokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/{commentID}")
	public ResponseEntity<String> deletePokeComment(@PathVariable int commentID) throws RecordNotFoundException {
		pokeCommServ.deletePokeComment(commentID);
		return ResponseEntity.ok("The record with the id of [" + commentID + "] has been deleted successfully!");
	}

	@PostMapping(path = "/rate")
	public ResponseEntity<RatePokeComm> ratePokeComment(@RequestBody RatePokeComm newRatePokeComm)
			throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RatePokeComm result = pokeCommServ.ratePokeComment(newRatePokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unrate")
	public ResponseEntity<String> unratePokeComment(@RequestBody Map<String, String> body)
			throws RecordNotFoundException {
		try {
			int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			pokeCommServ.unratePokeComment(new PokeCommIDDTO(commentID), new UserIDDTO(userID));
			return ResponseEntity
					.ok("User[" + userID + "] has unreported PokeComment[" + commentID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}

	@PostMapping(path = "/report")
	public ResponseEntity<ReportPokeComm> reportPokeComment(@RequestBody ReportPokeComm newReportPokeComm)
			throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportPokeComm result = pokeCommServ.reportPokeComment(newReportPokeComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unreport")
	public ResponseEntity<String> unreportPokeComment(@RequestBody Map<String, String> body)
			throws RecordNotFoundException {
		try {
			int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			pokeCommServ.unreportPokeComment(new PokeCommIDDTO(commentID), new UserIDDTO(userID));
			return ResponseEntity
					.ok("User[" + userID + "] has unreported PokeComment[" + commentID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}
}
