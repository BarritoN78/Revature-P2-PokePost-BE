package com.personal.revaturep2pokepostbe.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.personal.revaturep2pokepostbe.RevatureP2PokepostBeApplication;
import com.personal.revaturep2pokepostbe.exceptions.AuthenticationFailedException;
import com.personal.revaturep2pokepostbe.exceptions.EmailAlreadyExistsException;
import com.personal.revaturep2pokepostbe.exceptions.RecordNotFoundException;
import com.personal.revaturep2pokepostbe.exceptions.UsernameAlreadyExistsException;
import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.models.dtos.UserDTO;
import com.personal.revaturep2pokepostbe.repositories.UserRepository;

@SpringBootTest(classes=RevatureP2PokepostBeApplication.class)
public class UserServiceTests implements UserInterface {
	private final UserRepository userRepo;

	public UserServiceTests(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public User getUserByCredentials(String email, String password) throws AuthenticationFailedException {
		Optional<User> result = userRepo.findByEmailAndPassword(email, password);
		if (result.isPresent()) {
			return result.get();
		} else {
			throw new AuthenticationFailedException(email);
		}
	}

	@Override
	public User getUser(int userID) throws RecordNotFoundException {
		try {
			return userRepo.findById(userID).get();
		} catch (Exception e) {
			throw new RecordNotFoundException("User", userID);
		}
	}

	@Override
	public User registerUser(User newUser) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
		if (!doesEmailExist(newUser)) {
			if (!doesUsernameExist(newUser)) {
				return userRepo.save(newUser);
			}
		}
		return null;
	}

	@Override
	public User updateUserDetails(UserDTO updatedUser)
			throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException {
		try {
			User originalUser = userRepo.findById(updatedUser.getId()).get();
			User newUser = new User(updatedUser);
			newUser.setPassword(originalUser.getPassword());
			newUser.setRole(originalUser.getRole());
			if (!doesEmailExist(newUser)) {
				if (!doesUsernameExist(newUser)) {
					return userRepo.save(newUser);
				}
			}
		} catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User", updatedUser.getId());
		}
		return null;
	}

	@Override
	public User updateUserPassword(User updatedUser)
			throws UsernameAlreadyExistsException, EmailAlreadyExistsException, RecordNotFoundException {
		try {
			if (!doesEmailExist(updatedUser)) {
				if (!doesUsernameExist(updatedUser)) {
					return userRepo.save(updatedUser);
				}
			}
		} catch (NoSuchElementException e) {
			throw new RecordNotFoundException("User", updatedUser.getId());
		}
		return null;
	}

	@Override
	public boolean deleteUser(int userID) throws RecordNotFoundException {
		try {
			userRepo.deleteById(userID);
			return true;
		} catch (Exception e) {
			throw new RecordNotFoundException("User", userID);
		}
	}

	@Override
	public boolean doesEmailExist(User user) throws EmailAlreadyExistsException {
		Optional<User> result = userRepo.findByEmail(user.getEmail());
		if (result.isPresent()) {
			if (result.get().getId() != user.getId()) {
				throw new EmailAlreadyExistsException(user.getEmail());
			}
		}
		return false;
	}

	@Override
	public boolean doesUsernameExist(User user) throws UsernameAlreadyExistsException {
		Optional<User> result = userRepo.findByUsername(user.getUsername());
		if (result.isPresent()) {
			if (result.get().getId() != user.getId()) {
				throw new UsernameAlreadyExistsException(user.getUsername());
			}
		}
		return false;
	}

}
