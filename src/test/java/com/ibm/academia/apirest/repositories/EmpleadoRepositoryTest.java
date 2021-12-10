package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;

@DataJpaTest
public class EmpleadoRepositoryTest {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Test
	@DisplayName("Test: Buscar empleado por tipo")
	void findEmpleadoByTipoEmpleado() {

		// Given

		empleadoRepository.save(DatosDummy.empleado01());
		empleadoRepository.save(DatosDummy.empleado02());
		empleadoRepository.save(DatosDummy.empleado03());

		// When
		Iterable<Persona> expected = empleadoRepository.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);

		// Then
		assertThat(((List<Persona>) expected).size() == 2).isTrue();

	}

}
