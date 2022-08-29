package com.personal.revaturep2pokepostbe.models.dtos;

import com.personal.revaturep2pokepostbe.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private int id;
	private String email;
	private String username;
	private String profileImage;
	private String firstName;
	private String lastName;
	
	/*Custom Constructor*/
	
	/**
	 * A constructor used to convert a User object into a UserDTO object
	 * @param convertUser
	 */
	public UserDTO(User convertUser) {
		this.id = convertUser.getId();
		this.email = convertUser.getEmail();
		this.username = convertUser.getUsername();
		this.profileImage = convertUser.getProfileImage();
		this.firstName = convertUser.getFirstName();
		this.lastName = convertUser.getLastName();
	}
}
