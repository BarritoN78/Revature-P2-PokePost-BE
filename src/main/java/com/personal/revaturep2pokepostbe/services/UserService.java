package com.personal.revaturep2pokepostbe.services;

import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;
import com.personal.revaturep2pokepostbe.repositories.UserRepository;

/**
 * A service class for the manipulation of users
 * 
 * @author Barry Norton
 *
 */
@Service
public class UserService implements UserInterface {
	private final UserRepository userRepo;

	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User getUserByCredentials(String email, String password) {
		return userRepo.findByEmailAndPassword(email, password);
	}

	@Override
	public User getUser(int userID) {
		try {
			return userRepo.findById(userID).get();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User registerUser(User newUser) {
		// TODO Username and Email already exists exceptions
		try {
			return userRepo.save(newUser);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User updateUserDetails(UserDTO updatedUser) {
		// TODO Username and Email already exists exceptions
		try {
			User originalUser = userRepo.findById(updatedUser.getId()).get();
			User newUser = new User(updatedUser);
			newUser.setPassword(originalUser.getPassword());
			newUser.setRole(originalUser.getRole());
			return userRepo.save(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User updateUserPassword(User updatedUser) {
		// TODO Username and Email already exists exceptions
		try {
			return userRepo.save(updatedUser);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean deleteUser(int userID) {
		try {
			userRepo.deleteById(userID);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
