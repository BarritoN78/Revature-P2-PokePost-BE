package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportPokeComm;

@Repository
public interface ReportPokeCommRepository extends JpaRepository<ReportPokeComm, Integer>{

	boolean deleteByCommentIDAndUserID(int commentID, int userID);

}
