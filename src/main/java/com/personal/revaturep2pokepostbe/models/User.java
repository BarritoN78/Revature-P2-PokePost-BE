package com.personal.revaturep2pokepostbe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String email;
	private String username;
	private String password;
	private String profileImage;
	private String firstName;
	private String lastName;
	private String role;
	
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
}
