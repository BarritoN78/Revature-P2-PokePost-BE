package com.personal.revaturep2pokepostbe.models;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.Data;

@Data
@Entity
public class ArtComment {
	private int id;
	private int author;
	private String content;
	private int likes;
	private int reports;
	private Date postDate;
}
