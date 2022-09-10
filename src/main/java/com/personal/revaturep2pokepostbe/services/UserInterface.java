package com.personal.revaturep2pokepostbe.services;

import com.personal.revaturep2pokepostbe.exceptions.AuthenticationFailedException;
import com.personal.revaturep2pokepostbe.exceptions.EmailAlreadyExistsException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.UsernameAlreadyExistsException;
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
	 * @throws AuthenticationFailedException 
	 */
	public User getUserByCredentials(String email, String password) throws AuthenticationFailedException;
	
	/**
	 * Retrieves a user with the given ID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public User getUser(int userID) throws RecordNotFoundException;
	
	/**
	 * Registers a new user
	 * @param newUser
	 * @return
	 * @throws UsernameAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 */
	public User registerUser(User newUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException;
	
	/**
	 * Updates an existing user's details
	 * @param updatedUser
	 * @return
	 * @throws UsernameAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 * @throws RecordNotFoundException 
	 */
	public User updateUserDetails(UserDTO updatedUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException;
	
	/**
	 * Updates an existing user's password
	 * @param updatedUser
	 * @return
	 * @throws UsernameAlreadyExistsException
	 * @throws EmailAlreadyExistsException
	 * @throws RecordNotFoundException 
	 */
	public User updateUserPassword(User updatedUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException;
	
	/**
	 * Deletes a user with the given ID
	 * @param userID
	 * @return
	 * @throws RecordNotFoundException 
	 */
	public boolean deleteUser(int userID) throws RecordNotFoundException;
	
	/**
	 * Searches database for a record with a given user's email.
	 * If a record exists, compares the id of the given user to that of the record.
	 * @param email
	 * @return
	 * @throws EmailAlreadyExistsException
	 */
	public boolean doesEmailExist(User user) throws EmailAlreadyExistsException;
	
	/**
	 * Searches database for a record with a given user's username.
	 * If a record exists, compares the id of the given user to that of the record.
	 * @param email
	 * @return
	 * @throws UsernameAlreadyExistsException
	 */
	public boolean doesUsernameExist(User user) throws UsernameAlreadyExistsException;
}
