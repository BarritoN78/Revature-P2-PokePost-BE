package com.personal.revaturep2pokepostbe.repositories;

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
	User findByEmailAndPassword(String email, String password);

}
