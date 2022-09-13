package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.Fanart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fanart")
public class ArtIDDTO {
	@Id
	private int id;

	/*Custom Constructors*/
	
	public ArtIDDTO(Fanart convertArt) {
		this.id = convertArt.getId();
	}
}
