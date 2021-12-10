package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Pabellon;

@DataJpaTest
public class PabellonRepositoryTest {
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		pabellonRepository.save(DatosDummy.pabellon01());
		pabellonRepository.save(DatosDummy.pabellon02());
		pabellonRepository.save(DatosDummy.pabellon03());
	}
	
	@AfterEach
	void tearDown() {
		pabellonRepository.deleteAll();
	}
	
	/*@Test
	@DisplayName("Test: Buscar Pabellones por Localidad")
	void findPabellonesByLocalidad() {
		
		//Given
		
		//When
		
		Iterable<Pabellon> expected = pabellonRepository.findPabellonesByLocalidad("Compras");
		
		//When
		
		assertThat(((List<Pabellon>)expected).size() == 2).isTrue();
		
		
	}*/
	
	@Test
	@DisplayName("Test: Buscar Pabellon por Carrera")
	void findPabellonByNombre() {
		
		//Given
		
		//When
		
		Iterable<Pabellon> expected = pabellonRepository.findPabellonByNombre("Compras");
		
		//When
		
		assertThat(((List<Pabellon>)expected).size() == 2).isTrue();
		
	}

}
