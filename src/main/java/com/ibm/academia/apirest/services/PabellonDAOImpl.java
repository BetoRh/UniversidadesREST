package com.ibm.academia.apirest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.academia.apirest.entities.Pabellon;
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
	public Iterable<Pabellon> findPabellonesByLocalidad(String localidad) {
		
		return pabellonDAO.findPabellonesByLocalidad(localidad);
	}*/

	@Override
	public Iterable<Pabellon> findPabellonByNombre(String nombre) {
		
		return pabellonDAO.findPabellonByNombre(nombre);
	}



}
