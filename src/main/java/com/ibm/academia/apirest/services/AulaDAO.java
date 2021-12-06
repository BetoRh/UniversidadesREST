package com.ibm.academia.apirest.services;

import org.springframework.data.repository.query.Param;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;


public interface AulaDAO extends GenericoDAO<Aula> {
	
	public Iterable<Aula> buscarAulaPorTipoPizzarra(String pizarron);
	
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre);
	
	public Iterable<Aula> buscarAulaporNumero(@Param("numero_aula") Integer numero_aula);

}
