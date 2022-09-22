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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyRatedException;
import com.personal.revaturep2pokepostbe.exceptions.AlreadyReportedException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.SaveFailedException;
import com.personal.revaturep2pokepostbe.models.ArtComment;
import com.personal.revaturep2pokepostbe.models.RateArtComm;
import com.personal.revaturep2pokepostbe.models.ReportArtComm;
import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;
import com.personal.revaturep2pokepostbe.services.ArtCommService;

@WebMvcTest(controllers = ArtCommController.class)
public class ArtCommControllerTests {
	@MockBean
	private ArtCommService artCommServ;

	private ObjectMapper objMapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	/* --------------------------- */
	/* getArtCommentsByArtId Tests */
	/* --------------------------- */

	@Test
	void getArtCommentsByArtId_Ok() throws Exception {
		List<ArtComment> mockResults = new ArrayList<ArtComment>();
		mockResults.add(new ArtComment());
		Page<ArtComment> mockPage = new PageImpl<ArtComment>(mockResults);
		
		Mockito.when(artCommServ.getArtCommentsByArtID(1, 0, 5)).thenReturn(mockPage);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/comment/1?page=0&size=5"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void getArtCommentsByArtId_NegativePageNotFound() throws Exception {
		Mockito.when(artCommServ.getArtCommentsByArtID(1, -1, 5)).thenThrow(new IllegalArgumentException());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/comment/1?page=-1&size=5"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	void getArtCommentByArtId_NoContentPageNotFound() throws Exception {
		List<ArtComment> mockResults = new ArrayList<ArtComment>();
		Page<ArtComment> mockPage = new PageImpl<ArtComment>(mockResults);
		
		Mockito.when(artCommServ.getArtCommentsByArtID(1, 99, 5)).thenReturn(mockPage);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/fanart/comment/1?page=99&size=5"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	/* -------------------- */
	/* postArtComment Tests */
	/* -------------------- */

	@Test
	void postArtComment_Ok() throws Exception {
		ArtComment mockComm = new ArtComment(1, new ArtIDDTO(1), new UserIDDTO(1), "mock comment", 0, 0,
				Date.valueOf("2000-01-01"));
		
		Mockito.when(artCommServ.postArtComment(mockComm)).thenReturn(mockComm);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockComm))).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void postArtComment_SaveFailed() throws Exception {
		ArtComment mockComm = new ArtComment(1, new ArtIDDTO(1), new UserIDDTO(1), "mock comment", 0, 0,
				Date.valueOf(LocalDate.now()));

		Mockito.when(artCommServ.postArtComment(mockComm)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockComm)))
				.andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}

	/* ---------------------- */
	/* deleteArtComment Tests */
	/* ---------------------- */

	@Test
	void deleteArtComment_Ok() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void deleteArtComment_RecordNotFound() throws Exception {
		Mockito.when(artCommServ.deleteArtComment(99)).thenThrow(new RecordNotFoundException());		
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/99"))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	/* -------------------- */
	/* rateArtComment Tests */
	/* -------------------- */
	
	@Test
	void rateArtComment_Ok() throws Exception {
		RateArtComm mockRate = new RateArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1));

		Mockito.when(artCommServ.rateArtComment(mockRate)).thenReturn(mockRate);

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(mockRate)));
	}
	
	@Test
	void rateArtComment_AlreadyRated() throws Exception {
		RateArtComm mockRate = new RateArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artCommServ.rateArtComment(mockRate)).thenThrow(new AlreadyRatedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void rateArtComment_RecordNotFound() throws Exception {
		RateArtComm mockRate = new RateArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artCommServ.rateArtComment(mockRate)).thenThrow(new RecordNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void rateArtComment_SaveFailed() throws Exception {
		RateArtComm mockRate = new RateArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1));
		
		Mockito.when(artCommServ.rateArtComment(mockRate)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/rate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockRate))).andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}	

	/* ---------------------- */
	/* unrateArtComment Tests */
	/* ---------------------- */

	@Test
	void unrateArtComment_Ok() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "1");
		mockBody.put("userID", "1");
		
		Mockito.when(artCommServ.unrateArtComment(new ArtCommIDDTO(1), new UserIDDTO(1))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void unrateArtComment_RecordNotFound() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "1");
		mockBody.put("userID", "-1");
		
		Mockito.when(artCommServ.unrateArtComment(new ArtCommIDDTO(1), new UserIDDTO(-1))).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void unrateArtComment_InvalidNumberFormat() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "One");
		mockBody.put("userID", "1..00");

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unrate").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	/* ---------------------- */
	/* reportArtComment Tests */
	/* ---------------------- */
	
	@Test
	void reportArtComment_Ok() throws Exception {
		ReportArtComm mockReport = new ReportArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1), "Explicit Content");

		Mockito.when(artCommServ.reportArtComment(mockReport)).thenReturn(mockReport);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().json(objMapper.writeValueAsString(mockReport)));
	}
	
	@Test
	void reportArtComment_AlreadyReported() throws Exception {
		ReportArtComm mockReport = new ReportArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artCommServ.reportArtComment(mockReport)).thenThrow(new AlreadyReportedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void reportArtComment_RecordNotFound() throws Exception {
		ReportArtComm mockReport = new ReportArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artCommServ.reportArtComment(mockReport)).thenThrow(new RecordNotFoundException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void reportArtComment_SaveFailed() throws Exception {
		ReportArtComm mockReport = new ReportArtComm(1, new ArtCommIDDTO(1), new UserIDDTO(1), "Explicit Content");
		
		Mockito.when(artCommServ.reportArtComment(mockReport)).thenThrow(new SaveFailedException());

		mockMvc.perform(MockMvcRequestBuilders.post("/fanart/comment/report").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockReport))).andExpect(MockMvcResultMatchers.status().is5xxServerError());
	}
	
	/* ------------------------ */
	/* unreportArtComment Tests */
	/* ------------------------ */
	
	@Test
	void unreportArtComment_Ok() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "1");
		mockBody.put("userID", "1");
		
		Mockito.when(artCommServ.unreportArtComment(new ArtCommIDDTO(1), new UserIDDTO(1))).thenReturn(true);

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void unreportArtComment_RecordNotFound() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "1");
		mockBody.put("userID", "-1");		

		Mockito.when(artCommServ.unreportArtComment(new ArtCommIDDTO(1), new UserIDDTO(-1))).thenThrow(new RecordNotFoundException());
		
		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
	
	@Test
	void unreportArtComment_InvalidNumberFormat() throws Exception {
		Map<String, String> mockBody = new HashMap<String, String>();
		mockBody.put("commentID", "One");
		mockBody.put("userID", "1..00");

		mockMvc.perform(MockMvcRequestBuilders.delete("/fanart/comment/unreport").contentType(MediaType.APPLICATION_JSON)
				.content(objMapper.writeValueAsString(mockBody))).andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}
}
