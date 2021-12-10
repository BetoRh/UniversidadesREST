package com.ibm.academia.apirest.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.stubbing.Answer;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.repositories.PersonaRepository;

public class PersonaDAOImplTest {
	
	PersonaDAO personaDAO;
	PersonaRepository personaRepository;
	
	void setUp() {
		personaRepository = mock(PersonaRepository.class);
		personaDAO = new PersonaDAOImpl(personaRepository);		
	}
	
	@Test
	@DisplayName("Buscar por Nombre y Apellido")
	void buscarPorNombreYApellido() {
		
		//Given
		String nombre = "Jose";
		String apellido = "Reyes";
		
		
		when(personaRepository.buscarPorNombreYApellido(nombre, apellido))
		.thenReturn(Optional.empty());
		
		//When
		Optional<Persona> expected = personaDAO.buscarPorNombreYApellido(nombre, apellido);
		
		//Then
		assertThat(expected.get()).isEqualTo(DatosDummy.empleado01());
		
		
		
		
	}
	
	@Test
	@DisplayName("Buscar por Dni")
	@Disabled
	void buscarPorDni() {
		
		//Given
		Integer dni = 255501;
		
		when(personaRepository.buscarPorDni(dni)).then((Answer<?>) Arrays.asList(DatosDummy.empleado01()));
		
		//When
		Optional<Persona> expected = personaDAO.buscarPorDni(dni);
		
		//Then
		
		assertThat(expected.get()).isEqualTo(DatosDummy.empleado01());
		
	}
	@Test
	@DisplayName("Buscar Persona por Apellido")
	@Disabled
	void buscarPersonaPorApellido() {
		
		//Given
		String apellido = "Rivas";
		
		when(personaRepository.buscarPersonaPorApellido(apellido)).thenReturn(Arrays.asList(DatosDummy.profesor01(), DatosDummy.profesor02()));
		
		//When
		
		List<Persona> expected = (List<Persona>) personaDAO.buscarPersonaPorApellido(apellido);
		
		//Then
		
		assertThat(expected.get(0)).isEqualTo(DatosDummy.profesor01());
		assertThat(expected.get(1)).isEqualTo(DatosDummy.profesor02());

	}
	

}
