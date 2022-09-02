package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "pokecomment")
public class PokeCommIDDTO {
	private int id;
}
