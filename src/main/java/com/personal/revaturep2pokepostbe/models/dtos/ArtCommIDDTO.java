package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "artcomment")
public class ArtCommIDDTO {
	private int id;
}
