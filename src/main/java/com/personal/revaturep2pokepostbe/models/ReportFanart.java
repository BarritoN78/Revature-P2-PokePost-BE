package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.personal.revaturep2pokepostbe.models.dtos.ArtIDDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReportFanart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private ArtIDDTO artID;
	@ManyToOne
	private UserIDDTO userID;
	private String reason;
}
