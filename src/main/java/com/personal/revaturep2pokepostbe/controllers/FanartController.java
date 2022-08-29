package com.personal.revaturep2pokepostbe.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.services.FanartService;

@RestController
@RequestMapping("/fanart")
public class FanartController {
	private final FanartService artServ;
	
	public FanartController(FanartService artServ) {
		this.artServ = artServ;
	}
	
	@PostMapping
	public ResponseEntity<Fanart> postFanart(@RequestBody Fanart newFanart){
		Fanart result = artServ.postFanart(newFanart);
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/{artID}")
	public ResponseEntity<Boolean> deleteFanart(@PathVariable int artID) {
		boolean result = artServ.deleteFanart(artID);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/{artID}")
	public ResponseEntity<Fanart> getFanartByID(@PathVariable int artID){
		Fanart result = artServ.getFanartByID(artID);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping
	public ResponseEntity<List<Fanart>> getAllFanart(){
		List<Fanart> result = artServ.getAllFanart();
		return ResponseEntity.ok(result);
	}
}
