package com.ibm.academia.apirest.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class EmpleadoDAOImplTest {

	EmpleadoDAO empleadoDAO;
	@Qualifier("repositorioEmpleados")
	PersonaRepository empleadoRepository;

	void setUp() {
		empleadoRepository = mock(EmpleadoRepository.class);
		empleadoDAO = new EmpleadoDAOImpl(empleadoRepository);

	}

	@Test
	@DisplayName("Test: Buscar Empleado por Tipo de Empleado")
	void findEmpleadoByTipoEmpleado() {

		// Given

		TipoEmpleado tipoEmpleado = TipoEmpleado.ADMINISTRATIVO;

		when(((EmpleadoRepository) empleadoRepository).findEmpleadoByTipoEmpleado(tipoEmpleado))
				.thenReturn(Arrays.asList(DatosDummy.empleado01(), DatosDummy.empleado02()));

		// When

		Iterable<Persona> expected = empleadoDAO.findEmpleadoByTipoEmpleado(tipoEmpleado);

		// Then

		assertThat(((List<Persona>) expected).get(0)).isEqualTo(DatosDummy.empleado01());
		assertThat(((List<Persona>) expected).get(1)).isEqualTo(DatosDummy.empleado02());

		((EmpleadoRepository) verify(empleadoRepository)).findEmpleadoByTipoEmpleado(tipoEmpleado);

	}

}
