package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RateFanart {
	private int id;
	private int artID;
	private int userID;
}
