package com.ibm.academia.apirest.services;

import org.springframework.data.repository.query.Param;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;


public interface AulaDAO extends GenericoDAO<Aula> {
	
	//public Iterable<Aula> buscarAulaPorTipoPizzarra(Pizarron pizarron);
	
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre);
	
	public Iterable<Aula> buscarAulaporNumero(Integer numero_aula);
	
	public Aula actualizar(Aula aulaEncontrada, Aula aula);

}
