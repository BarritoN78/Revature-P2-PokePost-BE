package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.ArtComment;

import lombok.Data;

@Data
@Entity
@Table(name = "artcomment")
public class ArtCommIDDTO {
	@Id
	private int id;

	/*Custom Constructors*/
	
	public ArtCommIDDTO(ArtComment convertArtComm) {
		this.id = convertArtComm.getId();
	}
}
