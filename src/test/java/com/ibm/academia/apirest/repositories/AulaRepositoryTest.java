package com.ibm.academia.apirest.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.academia.apirest.datos.DatosDummy;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Pabellon;

@DataJpaTest
public class AulaRepositoryTest {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	@Autowired
	private PabellonRepository pabellonRepository;
	
	@BeforeEach
	void setUp() {
		
		aulaRepository.save(DatosDummy.aula01());
		aulaRepository.save(DatosDummy.aula02());
		aulaRepository.save(DatosDummy.aula03());
	}
	
	@AfterEach
	void tearDown() {
		
		aulaRepository.deleteAll();
	}
	
	//@Test
	//@DisplayName("Test: Buscar Aula por tipo Pizarra")
	//@Disabled
	/*void buscarAulaPorTipoPizzarra() {
		
		//Given
		
		//When
		
		Iterable<Aula> expected = aulaRepository.buscarAulaPorTipoPizzarra(Pizarron.PIZARRA_TIZA);
		
		//Then
		
		assertThat(((List<Aula>)expected).size() == 1).isTrue();
		
	}*/
	
	@Test
	@DisplayName("Test: Buscar Aulas por Nombre Pabellon")
	@Disabled
	void buscarAulasPorNombrePabellon() {
		
		//Given
		List<Aula> listaAulas = new ArrayList<>();
		listaAulas.add(DatosDummy.aula01());
		listaAulas.add(DatosDummy.aula02());
		listaAulas.add(DatosDummy.aula03());
		
		Iterable<Aula> aulas = aulaRepository.saveAll(listaAulas);
		
	
		Pabellon pabellon = pabellonRepository.save(DatosDummy.pabellon01());
		aulas.forEach(aula ->((Aula)aula).setPabellon(pabellon));
		aulaRepository.saveAll(aulas);
		
		//When
		
		String nombrePabellon = "Compras";
		Iterable<Aula> expected = aulaRepository.buscarAulasPorNombrePabellon(nombrePabellon);
		
		//Then
		
		assertThat(((List<Aula>) expected).size() == 3).isTrue();
		
		
	}
	
	@Test
	@DisplayName("Test: Buscar Aula por Numero")
	void buscarAulaporNumero() {
		
		//Given
		
		//When
		List<Aula> expected = (List<Aula>) aulaRepository.buscarAulaporNumero(1);
		
		//Then
		assertThat(((List<Aula>) expected).size() == 2).isTrue(); 
		
	}

}
