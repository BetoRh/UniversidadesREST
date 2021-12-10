package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.repositories.AulaRepository;

@Service
public class AulaDAOImpl extends GenericoDAOImpl<Aula, AulaRepository> implements AulaDAO {
	
	@Autowired
	private AulaRepository aulaRepository;
	
	
	@Autowired
	public AulaDAOImpl(AulaRepository repository) {
		super(repository);
		
	}

	/*@Override
	@Transactional
	public Iterable<Aula> buscarAulaPorTipoPizzarra(Pizarron pizarron) {
		
		return aulaRepository.buscarAulaPorTipoPizzarra(pizarron);
	}*/

	
	@Override
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre) {
		
		return aulaRepository.buscarAulasPorNombrePabellon(nombre);
	}
	
	@Override
	public Iterable<Aula> buscarAulaporNumero(Integer numero_aula) {
		
		return aulaRepository.buscarAulaporNumero(numero_aula);
	}

	@Override
	public Aula actualizar(Aula aulaEncontrada, Aula aula) {
		
		Aula aulaActualizada = null;
		aulaEncontrada.setNumeroAula(aula.getNumeroAula());
		aulaEncontrada.setCantidadPupitres(aula.getCantidadPupitres());;
		aulaEncontrada.setPizarron(aula.getPizarron());
		aulaActualizada = repository.save(aulaEncontrada);
		return aulaActualizada;
	}


}
