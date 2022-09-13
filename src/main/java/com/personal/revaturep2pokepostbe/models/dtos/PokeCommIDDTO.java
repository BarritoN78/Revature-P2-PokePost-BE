package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.PokeComment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pokecomment")
public class PokeCommIDDTO {
	@Id
	private int id;

	/*Custom Constructors*/
	
	public PokeCommIDDTO(PokeComment convertPokeComm) {
		this.id = convertPokeComm.getId();
	}
}
