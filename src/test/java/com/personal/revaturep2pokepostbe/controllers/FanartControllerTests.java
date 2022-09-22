package com.personal.revaturep2pokepostbe.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.PageNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.RateFanart;
import com.personal.revaturep2pokepostbe.models.ReportFanart;
import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.services.FanartService;


@WebMvcTest(controllers = FanartController.class)
public class FanartControllerTests {
	@MockBean
	private FanartService artServ;

	private ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	/* ---------------- */
	/* postFanart Tests */
	/* ---------------- */

	@Test
	void postFanart_Ok() throws Exception {
		Fanart mockArt = new Fanart(1, new User(), "mock img url", "mock title", "mock tags", 0, 0,
				Date.valueOf("2000-01-01"));
		
		Mockito.when(artServ.postFanart(mockArt)).thenReturn(mockArt);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/fanart").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockArt))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void postFanart_SaveFailed() throws Exception {
		Fanart mockArt = new Fanart(1, new User(), "mock img url", "mock title", "mock tags", 0, 0,
				Date.valueOf("2000-01-01"));

		Mockito.when(artServ.postFanart(mockArt)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockArt)))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}

	/* ---------------------- */
	/* deleteFanart Tests */
	/* ---------------------- */

	@Test
	void deleteFanart_Ok() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void deleteFanart_RecordNotFound() throws Exception {
		Mockito.when(artServ.deleteFanart(99)).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/99"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	/* --------------- */
	/* getFanart Tests */
	/* --------------- */

	@Test
	void getFanart_Ok() throws Exception {
		Mockito.when(artServ.getFanartByID(1)).thenReturn(new ArtDTO());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getFanart_RecordNotFound() throws Exception {
		Mockito.when(artServ.getFanartByID(99)).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/99"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}


	@GetMapping("/{artID}")
	public ResponseEntity<ArtDTO> getFanartByID(@PathVariable int artID) throws RecordNotFoundException {
		ArtDTO result = artServ.getFanartByID(artID);
		return ResponseEntity.ok(result);
	}
	
	/* ------------------ */
	/* getAllFanart Tests */
	/* ------------------ */

	@Test
	void getAllFanart_Ok() throws Exception {
		List<ArtDTO> mockResults = new ArrayList<ArtDTO>();
		mockResults.add(new ArtDTO());
		Page<ArtDTO> mockPage = new PageImpl<ArtDTO>(mockResults);
		
		Mockito.when(artServ.getAllFanartByPage(0, 5)).thenReturn(mockPage);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/1?page=0&size=5"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getAllFanart_NegativePageNotFound() throws Exception {
		Mockito.when(artServ.getAllFanartByPage(-1, 5)).thenThrow(new IllegalArgumentException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/1?page=-1&size=5"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	void getArtDTOByArtId_NoContentPageNotFound() throws Exception {
		List<ArtDTO> mockResults = new ArrayList<ArtDTO>();
		Page<ArtDTO> mockPage = new PageImpl<ArtDTO>(mockResults);
		
		Mockito.when(artServ.getAllFanartByPage(99, 5)).thenReturn(mockPage);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/1?page=99&size=5"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
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
			
	/* ---------------- */
	/* rateFanart Tests */
	/* ---------------- */
	
	@Test
	void rateFanart_Ok() throws Exception {
		RateFanart mockRate = new RateFanart(1, new ArtIDDTO(1), new UserIDDTO(1));

		Mockito.when(artServ.rateFanart(mockRate)).thenReturn(mockRate);

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(mockRate)));
	}
	
	@Test
	void rateFanart_AlreadyRated() throws Exception {
		RateFanart mockRate = new RateFanart(1, new ArtIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artServ.rateFanart(mockRate)).thenThrow(new AlreadyRatedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void rateFanart_RecordNotFound() throws Exception {
		RateFanart mockRate = new RateFanart(1, new ArtIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artServ.rateFanart(mockRate)).thenThrow(new RecordNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void rateFanart_SaveFailed() throws Exception {
		RateFanart mockRate = new RateFanart(1, new ArtIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artServ.rateFanart(mockRate)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}	

	/* ------------------ */
	/* unrateFanart Tests */
	/* ------------------ */

	@Test
	void unrateFanart_Ok() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "1");
		mockBody.put("userID", "1");
		
		Mockito.when(artServ.unrateFanart(new ArtIDDTO(1), new UserIDDTO(1))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void unrateFanart_RecordNotFound() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "1");
		mockBody.put("userID", "-1");
		
		Mockito.when(artServ.unrateFanart(new ArtIDDTO(1), new UserIDDTO(-1))).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void unrateFanart_InvalidNumberFormat() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "One");
		mockBody.put("userID", "1..00");

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	/* ------------------ */
	/* reportFanart Tests */
	/* ------------------ */
	
	@Test
	void reportFanart_Ok() throws Exception {
		ReportFanart mockReport = new ReportFanart(1, new ArtIDDTO(1), new UserIDDTO(1), "Explicit Content");

		Mockito.when(artServ.reportFanart(mockReport)).thenReturn(mockReport);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(mockReport)));
	}
	
	@Test
	void reportFanart_AlreadyReported() throws Exception {
		ReportFanart mockReport = new ReportFanart(1, new ArtIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artServ.reportFanart(mockReport)).thenThrow(new AlreadyReportedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void reportFanart_RecordNotFound() throws Exception {
		ReportFanart mockReport = new ReportFanart(1, new ArtIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artServ.reportFanart(mockReport)).thenThrow(new RecordNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void reportFanart_SaveFailed() throws Exception {
		ReportFanart mockReport = new ReportFanart(1, new ArtIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artServ.reportFanart(mockReport)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	/* -------------------- */
	/* unreportFanart Tests */
	/* -------------------- */
	
	@Test
	void unreportFanart_Ok() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "1");
		mockBody.put("userID", "1");
		
		Mockito.when(artServ.unreportFanart(new ArtIDDTO(1), new UserIDDTO(1))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void unreportFanart_RecordNotFound() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "1");
		mockBody.put("userID", "-1");		

		Mockito.when(artServ.unreportFanart(new ArtIDDTO(1), new UserIDDTO(-1))).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void unreportFanart_InvalidNumberFormat() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("artID", "One");
		mockBody.put("userID", "1..00");

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
