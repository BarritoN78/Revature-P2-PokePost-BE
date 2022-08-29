package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.ReportArtComm;

@Repository
public interface ReportArtCommRepository extends JpaRepository<ReportArtComm, Integer>{

}
