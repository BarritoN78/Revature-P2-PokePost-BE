package com.personal.revaturep2pokepostbe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.personal.revaturep2pokepostbe.models.Fanart;

@Repository
public interface FanartRepository extends JpaRepository<Fanart, Integer>{

}
