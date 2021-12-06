package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	
	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
		
	}

	@Override
	public Iterable<Aula> buscarAulaPorTipoPizzarra(String pizarron) {
		
		return aulaRepository.buscarAulaPorTipoPizzarra(pizarron);
	}

	
	@Override
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre) {
		
		return aulaRepository.buscarAulasPorNombrePabellon(nombre);
	}
	
	@Override
	public Iterable<Aula> buscarAulaporNumero(@Param("numero_aula") Integer numero_aula) {
		
		return aulaRepository.buscarAulaporNumero(numero_aula);
	}


}
