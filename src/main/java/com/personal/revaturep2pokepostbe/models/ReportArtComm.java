package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.personal.revaturep2pokepostbe.models.dtos.ArtCommIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReportArtComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private ArtCommIDDTO commentID;
	@ManyToOne
	private UserIDDTO userID;
	private String reason;
}
