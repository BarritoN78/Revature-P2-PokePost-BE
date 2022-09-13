package com.personal.revaturep2pokepostbe.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.personal.revaturep2pokepostbe.models.dtos.ArtDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Fanart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	@ManyToOne(targetEntity = User.class)
	private User author;
	private String imageURL;
	private String title;
	private String tags;
	private int likes = 0;
	private int reports = 0;
	private Date postDate = Date.valueOf(LocalDate.now());
	
    /*Custom Constructors*/
	
	public Fanart(ArtDTO convertArt) {
		this.id = convertArt.getId();
		this.author = new User(convertArt.getAuthor());
		this.imageURL = convertArt.getImageURL();
		this.title = convertArt.getTitle();
		this.tags = convertArt.getTags();
		this.likes = convertArt.getLikes();
		this.reports = convertArt.getReports();
		this.postDate = convertArt.getPostDate();		
	}
}
