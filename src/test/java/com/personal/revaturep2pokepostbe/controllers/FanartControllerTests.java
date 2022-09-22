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
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.services.FanartService;


@WebMvcTest
public class FanartControllerTests {
	private final FanartService artServ;

	public FanartControllerTests(FanartService artServ) {
		this.artServ = artServ;
	}

	@PostMapping
	public ResponseEntity<Fanart> postFanart(@RequestBody ArtDTO newFanartDTO) throws SaveFailedException {
		Fanart newFanart = new Fanart(newFanartDTO);
		Fanart result = artServ.postFanart(newFanart);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{artID}")
	public ResponseEntity<String> deleteFanart(@PathVariable int artID) throws RecordNotFoundException {
		artServ.deleteFanart(artID);
		return ResponseEntity.ok("The record with the id of [" + artID + "] has been deleted successfully!");
	}

	@GetMapping("/{artID}")
	public ResponseEntity<ArtDTO> getFanartByID(@PathVariable int artID) throws RecordNotFoundException {
		ArtDTO result = artServ.getFanartByID(artID);
		return ResponseEntity.ok(result);
	}

	@GetMapping
	public ResponseEntity<Page<ArtDTO>> getAllFanart(@RequestParam int page, @RequestParam int size)
			throws PageNotFoundException {
		try {
			Page<ArtDTO> result = artServ.getAllFanartByPage(page, size);
			if (result.hasContent()) {
				return ResponseEntity.ok(result);
			} else {
				throw new PageNotFoundException(page);
			}
		} catch (IllegalArgumentException e) {
			throw new PageNotFoundException(page);
		}
	}

	@PostMapping(path = "/filter")
	public ResponseEntity<Page<ArtDTO>> getFilteredFanart(@RequestBody Map<String, String> filters,
			@RequestParam int page, @RequestParam int size) throws PageNotFoundException {
		try {
			Page<ArtDTO> result;
			String field = filters.get("field");
			String value = filters.get("value");
			switch (field) {
			case "title":
				result = artServ.getFanartByTitle(value, page, size);
				break;
			case "tags":
				result = artServ.getFanartByTags(value, page, size);
				break;
			case "dateAfter":
				result = artServ.getFanartByPostDate(value, true, page, size);
				break;
			case "dateBefore":
				result = artServ.getFanartByPostDate(value, false, page, size);
				break;
			default:
				result = artServ.getAllFanartByPage(page, size);
				break;
			}
			if (result.hasContent()) {
				return ResponseEntity.ok(result);
			} else {
				throw new PageNotFoundException(page);
			}
		} catch (IllegalArgumentException e) {
			throw new PageNotFoundException(page);
		}
	}

	@PostMapping(path = "/rate")
	public ResponseEntity<RateFanart> rate(@RequestBody RateFanart newRateArt) throws AlreadyRatedException, RecordNotFoundException, SaveFailedException {
		RateFanart result = artServ.rateFanart(newRateArt);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unrate")
	public ResponseEntity<String> unrateFanart(@RequestBody Map<String, String> body) throws RecordNotFoundException {
		try {
			int artID = Integer.parseInt(body.getOrDefault("artID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			artServ.unrateFanart(new ArtIDDTO(artID), new UserIDDTO(userID));
			return ResponseEntity.ok("User[" + userID + "] has unrated Art[" + artID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}

	@PostMapping(path = "/report")
	public ResponseEntity<ReportFanart> reportFanart(@RequestBody ReportFanart newReportArt) throws AlreadyReportedException, RecordNotFoundException, SaveFailedException {
		ReportFanart result = artServ.reportFanart(newReportArt);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unreport")
	public ResponseEntity<String> unreportFanart(@RequestBody Map<String, String> body) throws RecordNotFoundException {
		try {
			int artID = Integer.parseInt(body.getOrDefault("artID", "-1"));
			int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
			artServ.unreportFanart(new ArtIDDTO(artID), new UserIDDTO(userID));
			return ResponseEntity.ok("User[" + userID + "] has unreported Art[" + artID + "] successfully!");
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("The values of the given IDs must be valid integers");
		}
	}
}
