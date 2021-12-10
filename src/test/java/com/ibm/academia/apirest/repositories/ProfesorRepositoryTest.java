package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

@DataJpaTest
public class ProfesorRepositoryTest {
	
	@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaRepository profesoresRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	@DisplayName("Buscar Profesores por Carrera")
	void findProfesoresByCarrera(String nombre) {
		
		//Given
		List<Persona> listaProfesores = new ArrayList<>();
		listaProfesores.add(DatosDummy.profesor01());
		listaProfesores.add(DatosDummy.profesor02());
		listaProfesores.add(DatosDummy.profesor03());
		
		Iterable<Persona> personas = profesoresRepository.saveAll(listaProfesores);
		
		Set<Carrera> carrera = (Set<Carrera>) carreraRepository.save(DatosDummy.carrera01());
		personas.forEach(profesor -> ((Profesor)profesor).setCarreras(carrera));
		profesoresRepository.saveAll(personas);
		
		
		
		//When
		
		String nombreCarrera = "Ingenieria en Sistemas";
		List<Persona> expected = (List<Persona>) ((ProfesorRepository)profesoresRepository).findProfesoresByCarrera(nombreCarrera);
		
		//Then
		
		assertThat(expected.size() == 3).isTrue();
									  
		
	}

}
