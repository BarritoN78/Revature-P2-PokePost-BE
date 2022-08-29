package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Wishlist {
	private int id;
	private int pokeID;
	private int userID;
}
