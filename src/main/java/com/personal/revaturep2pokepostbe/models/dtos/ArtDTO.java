package com.personal.revaturep2pokepostbe.models.dtos;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.Fanart;
import com.personal.revaturep2pokepostbe.models.User;

import lombok.Data;

@Data
@Entity
@Table(name = "fanart")
public class ArtDTO {
	@Id
	private int id;
	@ManyToOne(targetEntity = User.class)
	private UserIDDTO author;
	private String imageURL;
	private String title;
	private String tags;
	private int likes;
	private int reports;
	private Date postDate = Date.valueOf(LocalDate.now());

	/*Custom Constructors*/
	
	public ArtDTO(Fanart convertArt) {
		this.id = convertArt.getId();
		this.author = new UserIDDTO(convertArt.getAuthor());
		this.imageURL = convertArt.getImageURL();
		this.title = convertArt.getTitle();
		this.tags = convertArt.getTags();
		this.likes = convertArt.getLikes();
		this.reports = convertArt.getReports();
		this.postDate = convertArt.getPostDate();
		
	}
}
