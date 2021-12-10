package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;

@DataJpaTest
public class AlumnoRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioAlumnos")
	private PersonaRepository alumnoRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	@DisplayName("Test: Buscar Alumno por Nombre Carrera")
	void buscarAlumnoPorNombreCarrera() {
		
		//Given
		List<Persona> listaPersonas = new ArrayList<Persona>();
		listaPersonas.add(DatosDummy.alumno01());
		listaPersonas.add(DatosDummy.alumno02());
		listaPersonas.add(DatosDummy.alumno03());
		
		Iterable<Persona> personas = alumnoRepository.saveAll(listaPersonas);
		
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01());
		personas.forEach(alumno -> ((Alumno)alumno).setCarrera(carrera));
		alumnoRepository.saveAll(personas);
		
		//When
		String carreraNombre = "Ingenieria en Sistemas";
		List<Persona> expected = (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(carreraNombre);
		
		//Then
		assertThat(expected.size() == 3).isTrue();
				
		
	}

}
