package com.ibm.academia.apirest.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.repositories.PabellonRepository;

@Service
public class PabellonDAOImpl extends GenericoDAOImpl<Pabellon, PabellonRepository> implements PabellonDAO {
	
	@Autowired
	private PabellonDAO pabellonDAO;
	
	@Autowired
	public PabellonDAOImpl(PabellonRepository repository) {
		super(repository);
	
	}

	/*@Override
	@Transactional
	public Iterable<Pabellon> findPabellonesByLocalidad(String localidad) {
		
		return pabellonDAO.findPabellonesByLocalidad(localidad);
	}*/

	@Override
	@Transactional
	public Iterable<Pabellon> findPabellonByNombre(String nombre) {
		
		return pabellonDAO.findPabellonByNombre(nombre);
	}

	@Override
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon) {
			
		Pabellon pabellonActualizado = null;
		pabellonEncontrado.setNombre(pabellonEncontrado.getNombre());
		pabellonEncontrado.setMetrosCuadrados(pabellonEncontrado.getMetrosCuadrados());
		pabellonEncontrado.setDireccion(pabellonEncontrado.getDireccion());
		pabellonActualizado = repository.save(pabellonEncontrado);
		return pabellonActualizado;
		
	}



}
