package com.ibm.academia.apirest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

class UniversidadesRest1ApplicationTests {
	
	Calculadora calculadora = new Calculadora();
	
	@Test
	@DisplayName("Test: Suma de valores")
	void sumarValor() {
		
		//Given --> Define el contexto o las precondiciones
		Integer ValorA = 2;
		Integer ValorB = 4;
		
		//When
		Integer expected = calculadora.suma(ValorA, ValorB);
		
		//Then
		
		Integer resultadoEsperado = 5;
		assertThat(expected).isEqualTo(resultadoEsperado);
		
	}
	
	class Calculadora{
		
		Integer suma(Integer a, Integer b) {
		return a + b;
		
		}
	}

}
