package com.ibm.academia.apirest.services;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;

public interface ProfesorDAO extends PersonaDAO{
	
	
	public Iterable<Persona> findProfesoresByCarrera(String nombre );

}
