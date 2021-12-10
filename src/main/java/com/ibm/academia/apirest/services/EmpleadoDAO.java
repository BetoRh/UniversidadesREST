package com.ibm.academia.apirest.services;


import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;

public interface EmpleadoDAO extends PersonaDAO {
	
	
	public Iterable<Persona>findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado);
	
	public Persona actualizar(Persona empleadoEncontrado, Persona empleado);
	
	public Persona asociarPabellonEmpleado(Persona empleado, Pabellon pabellon);
	


}
