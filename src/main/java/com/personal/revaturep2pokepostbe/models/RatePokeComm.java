package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class RatePokeComm {
	private int id;
	private int commentID;
	private int userID;
}
