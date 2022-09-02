package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "fanart")
public class ArtIDDTO {
	private int id;
}
