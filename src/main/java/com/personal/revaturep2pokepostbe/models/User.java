package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class User {
	private int id;
	private String email;
	private String username;
	private String password;
	private String profileImage;
	private String firstName;
	private String lastName;
	private String role;
}
