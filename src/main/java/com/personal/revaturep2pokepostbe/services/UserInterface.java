package com.personal.revaturep2pokepostbe.services;

import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;

/**
 * An interface to be followed by UserService
 * @author Barry Norton
 *
 */
public interface UserInterface {
	/**
	 * Retrieves a user with the given credentials
	 * @param email
	 * @param password
	 * @return
	 */
	public User getUserByCredentials(String email, String password);
	
	/**
	 * Retrieves a user with the given ID
	 * @param userID
	 * @return
	 */
	public User getUser(int userID);
	
	/**
	 * Registers a new user
	 * @param newUser
	 * @return
	 */
	public User registerUser(User newUser);
	
	/**
	 * Updates an existing user's details
	 * @param updatedUser
	 * @return
	 */
	public User updateUserDetails(UserDTO updatedUser);
	
	/**
	 * Updates an existing user's password
	 * @param updatedUser
	 * @return
	 */
	public User updateUserPassword(User updatedUser);
	
	/**
	 * Deletes a user with the given ID
	 * @param userID
	 * @return
	 */
	public boolean deleteUser(int userID);
}
