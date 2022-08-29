package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class Wishlist {
	private int id;
	private int pokeID;
	private int userID;
}
