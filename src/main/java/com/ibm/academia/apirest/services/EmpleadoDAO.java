package com.ibm.academia.apirest.services;

import org.springframework.data.jpa.repository.Query;

import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.enums.TipoEmpleado;

public interface EmpleadoDAO extends PersonaDAO {
	
	
	public Iterable<Persona>findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);

}
