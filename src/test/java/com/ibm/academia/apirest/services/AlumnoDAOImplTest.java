package com.ibm.academia.apirest.services;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.AlumnoRepository;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import com.ibm.academia.apirest.repositories.EmpleadoRepository;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class AlumnoDAOImplTest {
	
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	AlumnoDAO alumnoDAO;
	@Qualifier("repositorioAlumnos")
	PersonaRepository alumnoRepository;
	
	void setUp(){
		
		alumnoRepository = mock(AlumnoRepository.class);
		alumnoDAO = new AlumnoDAOImpl(alumnoRepository);
		
	}
	
	@Test
	@DisplayName("Test: Buscar Alumnos por Nombre Carrera" )
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
				
				when(((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera("Ingenieria en Sistemas"))
				.thenReturn(Arrays.asList(DatosDummy.alumno01(), DatosDummy.alumno02(),DatosDummy.alumno03() ));
				
				//When
				String carreraNombre = "Ingenieria en Sistemas";
				List<Persona> expected = (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnoPorNombreCarrera(carreraNombre);
				
				//Then
				
				assertThat(expected.get(0)).isEqualTo(DatosDummy.alumno01());
				assertThat(expected.get(1)).isEqualTo(DatosDummy.alumno02());
				assertThat(expected.get(2)).isEqualTo(DatosDummy.alumno03());
		
		
	}

}
