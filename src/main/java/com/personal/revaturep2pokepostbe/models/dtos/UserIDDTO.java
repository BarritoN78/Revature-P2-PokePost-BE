package com.personal.revaturep2pokepostbe.models.dtos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.User;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserIDDTO {
	@Id
	private int id;

	/*Custom Constructors*/
	
	public UserIDDTO(User convertUser) {
		this.id = convertUser.getId();
	}
}
