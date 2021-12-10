package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Persona;

@Repository("repositorioProfesores")
public interface ProfesorRepository extends PersonaRepository {
	
	@Query("select a from Profesor a join fetch Carrera c on c.nombre = ?1")
	public Iterable<Persona> findProfesoresByCarrera(String nombre);
	
	

}
