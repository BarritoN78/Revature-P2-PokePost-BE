package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RateArtComm {
	private int id;
	private int commentID;
	private int userID;
}
