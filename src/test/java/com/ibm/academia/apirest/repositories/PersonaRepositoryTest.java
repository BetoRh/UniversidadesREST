package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@DataJpaTest
public class PersonaRepositoryTest {

	@Autowired
	@Qualifier("repositorioAlumnos")
	private PersonaRepository alumnoRepository;

	@Autowired
	@Qualifier("repositorioEmpleados")
	private PersonaRepository empleadoRepository;

	@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesorRepository;

	@Test
	@DisplayName("Test: Buscar por nombre y apellido")
	void buscarPorNombreYApellido() {

		// Given
		Persona personaAlumno = alumnoRepository.save(DatosDummy.alumno01());
		// Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());
		// Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());
		// When
		Optional<Persona> expected01 = alumnoRepository.buscarPorNombreYApellido(DatosDummy.alumno01().getNombre(),
				DatosDummy.alumno01().getApellido());
		// Optional<Persona> expected02 =
		// empleadoRepository.buscarPorNombreYApellido(DatosDummy.empleado01().getNombre(),DatosDummy.empleado01().getApellido());
		// Optional<Persona> expected03 =
		// profesorRepository.buscarPorNombreYApellido(DatosDummy.profesor01().getNombre(),DatosDummy.profesor01().getApellido());

		// Then
		assertThat(expected01.get()).isInstanceOf(Alumno.class);
		assertThat(expected01.get()).isEqualTo(personaAlumno);

		// assertThat(expected02.get()).isInstanceOf(Empleado.class);
		// assertThat(expected02.get()).isEqualTo(personaEmpleado);

		// assertThat(expected03.get()).isInstanceOf(Profesor.class);
		// assertThat(expected03.get()).isEqualTo(personaProfesor);
	}
	@Test
	@DisplayName("Test: Buscar Persona por DNI")
	void buscarPorDni() {

		// Given
		// Persona personaAlumno = alumnoRepository.save(DatosDummy.alumno01());
		 Persona personaEmpleado = empleadoRepository.save(DatosDummy.empleado01());
		// Persona personaProfesor = profesorRepository.save(DatosDummy.profesor01());
		// When
		//Optional<Persona> expected01 = 
		//alumnoRepository.buscarPorDni(DatosDummy.alumno01().getId());
		Optional<Persona> expected02 =
		empleadoRepository.buscarPorDni(DatosDummy.empleado01().getId());
		// Optional<Persona> expected03 =
		// profesorRepository.buscarPorNombreYApellido(DatosDummy.profesor01().getNombre(),DatosDummy.profesor01().getApellido());

		// Then
		//assertThat(expected01.get()).isInstanceOf(Alumno.class);
		//assertThat(expected01.get()).isEqualTo(personaAlumno);
		//assertThat(expected01.get().getDni()).isEqualTo(personaAlumno);

		assertThat(expected02.get()).isInstanceOf(Empleado.class);
		assertThat(expected02.get()).isEqualTo(personaEmpleado);
		assertThat(expected02.get().getDni()).isEqualTo(personaEmpleado);

		// assertThat(expected03.get()).isInstanceOf(Profesor.class);
		// assertThat(expected03.get()).isEqualTo(personaProfesor);

	}
	@Test
	@DisplayName("Test: Buscar Persona por apellido")
	void buscarPersonaPorApellido() {
		//Given
		List<Persona> listaPersonas = new ArrayList<Persona>();
		listaPersonas.add(DatosDummy.profesor01());
		listaPersonas.add(DatosDummy.profesor02());
		listaPersonas.add(DatosDummy.profesor03());
		
		Iterable<Persona> personas = alumnoRepository.saveAll(listaPersonas);
		
		//When
		String apellido1 = "Rivas";
		List<Persona> expected = (List<Persona>) profesorRepository.buscarPersonaPorApellido(apellido1);
		
		//Then
		
		assertThat(expected.size() == 2).isTrue();
		

	}

}
