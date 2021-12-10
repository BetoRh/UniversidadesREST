package com.ibm.academia.apirest.services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

@Service
public class EmpleadoDAOImpl extends PersonaDAOImpl implements EmpleadoDAO{
	
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	@Autowired
	public EmpleadoDAOImpl(@Qualifier("repositorioEmpleados")PersonaRepository repository) {
		super(repository);
		
	}

	@Override
	@Transactional
	public Iterable<Persona> findEmpleadoByTipoEmpleado(TipoEmpleado tipoEmpleado) {
		
		return empleadoRepository.findEmpleadoByTipoEmpleado(tipoEmpleado);
	}

	@Override
	@Transactional
	public Persona actualizar(Persona empleadoEncontrado, Persona empleado) {
		
		Persona empledoActualizado = null;
		empleadoEncontrado.setNombre(empleado.getNombre());
		empleadoEncontrado.setApellido(empleado.getApellido());
		empleadoEncontrado.setDireccion(empleado.getDireccion());
		empledoActualizado = repository.save(empleadoEncontrado);
		return empledoActualizado;
		
	}

	@Override
	@Transactional
	public Persona asociarPabellonEmpleado(Persona empleado, Pabellon pabellon) {
		
		((Empleado)empleado).setPabellon(pabellon);
		return repository.save(empleado);
	}

	

}
