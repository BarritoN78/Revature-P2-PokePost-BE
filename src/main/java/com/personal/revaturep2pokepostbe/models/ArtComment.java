package com.personal.revaturep2pokepostbe.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ArtComment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToMany(targetEntity = Fanart.class)
	private int artId;
	@ManyToMany(targetEntity = User.class)
	private int author;
	private String content;
	private int likes;
	private int reports;
	private Date postDate;
}
