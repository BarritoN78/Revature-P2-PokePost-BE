package com.personal.revaturep2pokepostbe.services;

import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;

public class UserService implements UserInterface{

	@Override
	public User getUserByCredentials(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerUser(User newUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserDetails(UserDTO updatedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserPassword(User updatedUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(int userID) {
		// TODO Auto-generated method stub
		return false;
	}

}
