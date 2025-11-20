package com.test.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.backend.domain.Persona;


@Repository
public interface PersonaRepository extends JpaRepository<Persona, Integer> {
	
	Optional<Persona> findByIdentificacion(String identificacion);
	
}
