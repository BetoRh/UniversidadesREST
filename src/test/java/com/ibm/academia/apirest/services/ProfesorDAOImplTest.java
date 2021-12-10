package com.ibm.academia.apirest.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;
import com.ibm.academia.apirest.repositories.CarreraRepository;
import com.ibm.academia.apirest.repositories.ProfesorRepository;

public class ProfesorDAOImplTest {
	
	ProfesorDAO profesorDAO;
	ProfesorRepository profesorRepository;
	

	CarreraDAO carreraDAO;
	CarreraRepository carreraRepository;

	
	void setUp() {
		
		profesorRepository = (ProfesorRepository) mock(ProfesorDAOImpl.class);
		profesorDAO = new ProfesorDAOImpl(profesorRepository);
		carreraRepository = mock(CarreraRepository.class);
		carreraDAO = new CarreraDAOImpl(carreraRepository);
	}
	
	@Test
	@DisplayName("Buscar Profesores por Carrera")
	void findProfesoresByCarrera(String nombre ) {
		
		//Given
				List<Persona> listaProfesores = new ArrayList<>();
				listaProfesores.add(DatosDummy.profesor01());
				listaProfesores.add(DatosDummy.profesor02());
				listaProfesores.add(DatosDummy.profesor03());
				
				Iterable<Persona> personas = profesorRepository.saveAll(listaProfesores);
				
				Set<Carrera> carrera = (Set<Carrera>) carreraRepository.save(DatosDummy.carrera01());
				personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carrera));
				profesorRepository.saveAll(personas);
				
				String carreras = "Ingenieria en Sistemas";
				when(profesorRepository.findProfesoresByCarrera(carreras)).thenReturn(personas);
				
		//When
		List<Persona> expected = (List<Persona>) profesorDAO.findProfesoresByCarrera(carreras);	
		
		//Then
		
		assertThat(expected.get(0)).isEqualTo(DatosDummy.profesor01());
		assertThat(expected.get(0)).isEqualTo(DatosDummy.profesor02());
		assertThat(expected.get(0)).isEqualTo(DatosDummy.profesor03());
		
		
		
		
	}

}
