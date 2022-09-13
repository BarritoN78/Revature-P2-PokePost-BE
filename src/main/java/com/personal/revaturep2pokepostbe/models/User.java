package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;
import com.personal.revaturep2pokepostbe.models.dtos.UserIDDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private int id;
	private String email;
	private String username;
	private String password;
	private String profileImage = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/363.png";
	private String firstName;
	private String lastName;
	private String role = "user";
	
	/*Custom Constructor*/
	
	/**
	 * Converts a UserDTO object into a User object
	 * @param userDTO
	 */
	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.email = userDTO.getEmail();
		this.username = userDTO.getUsername();
		this.password = "";
		this.profileImage = userDTO.getProfileImage();
		this.firstName = userDTO.getFirstName();
		this.lastName = userDTO.getLastName();
		this.role = "";
	}

	public User(UserIDDTO userIDDTO) {
		this.id = userIDDTO.getId();
		this.email = "";
		this.username = "";
		this.password = "";
		this.profileImage = "";
		this.firstName = "";
		this.lastName = "";
		this.role = "";
	}
}
