package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Table;

import lombok.Data;

@Data
@Table(name = "user")
public class UserIDDTO {
	private int id;
}
