package com.personal.revaturep2pokepostbe.models;

import java.sql.Date;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fanart {
	private int id;
	private int author;
	private String imageURL;
	private String title;
	private String tags;
	private int likes;
	private int reports;
	private Date postDate;
}
