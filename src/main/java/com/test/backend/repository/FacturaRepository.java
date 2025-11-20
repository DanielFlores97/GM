package com.test.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.test.backend.domain.Factura;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {
	
	List<Factura> findByPersonaId(Integer id);

}
