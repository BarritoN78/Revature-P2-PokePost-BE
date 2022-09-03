package com.personal.revaturep2pokepostbe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	/**
	 * Returns the user associated with the given pair of email and password
	 * @param email
	 * @param password
	 * @return
	 */
	Optional<User> findByEmailAndPassword(String email, String password);
	
	/**
	 * Returns the user associated with the given email
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);
	
	/**
	 * Returns the user associated with the given username
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

}
