package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ReportArtComm {
	private int id;
	private int artID;
	private int userID;
	private String reason;
}
