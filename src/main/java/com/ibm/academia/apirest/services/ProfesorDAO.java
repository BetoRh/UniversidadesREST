package com.ibm.academia.apirest.services;



import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

public interface ProfesorDAO extends PersonaDAO{
	
	
	public Iterable<Persona> findProfesoresByCarrera(String nombre );
	
	public Persona actualizar(Persona profesorEncontrado, Persona profesor);
	
	public Persona asociarCarreraProfesor(Persona profesor, Carrera carrera);

}
