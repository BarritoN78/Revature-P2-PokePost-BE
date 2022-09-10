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

import com.personal.revaturep2pokepostbe.exceptions.PageNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.services.FanartService;

@RestController
@RequestMapping("/fanart")
public class FanartController {
	private final FanartService artServ;

	public FanartController(FanartService artServ) {
		this.artServ = artServ;
	}

	@PostMapping
	public ResponseEntity<Fanart> postFanart(@RequestBody Fanart newFanart) {
		Fanart result = artServ.postFanart(newFanart);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{artID}")
	public ResponseEntity<Boolean> deleteFanart(@PathVariable int artID) throws RecordNotFoundException {
		boolean result = artServ.deleteFanart(artID);
		return ResponseEntity.ok(result);
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
	public ResponseEntity<Page<ArtDTO>> getFilteredFanart(@RequestBody String field, @RequestParam int page,
			@RequestParam int size) throws PageNotFoundException {
		try {
			Page<ArtDTO> result;
			switch (field) {
			case "title":
				result = artServ.getAllFanartByPage(page, size);
				break;
			case "tags":
				result = artServ.getAllFanartByPage(page, size);
				break;
			case "author":
				result = artServ.getAllFanartByPage(page, size);
				break;
			case "dateAfter":
				result = artServ.getAllFanartByPage(page, size);
				break;
			case "dateBefore":
				result = artServ.getAllFanartByPage(page, size);
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
	public ResponseEntity<RateFanart> rate(@RequestBody RateFanart newRateArtComm) {
		RateFanart result = artServ.rateFanart(newRateArtComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unrate")
	public ResponseEntity<Boolean> unrateFanart(@RequestBody Map<String, String> body) {
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = artServ.unrateFanart(commentID, userID);
		return ResponseEntity.ok(result);
	}

	@PostMapping(path = "/report")
	public ResponseEntity<ReportFanart> reportFanart(ReportFanart newReportArtComm) {
		ReportFanart result = artServ.reportFanart(newReportArtComm);
		return ResponseEntity.ok(result);
	}

	@DeleteMapping(path = "/unreport")
	public ResponseEntity<Boolean> unreportFanart(@RequestBody Map<String, String> body) {
		int commentID = Integer.parseInt(body.getOrDefault("commentID", "-1"));
		int userID = Integer.parseInt(body.getOrDefault("userID", "-1"));
		Boolean result = artServ.unreportFanart(commentID, userID);
		return ResponseEntity.ok(result);
	}
}
