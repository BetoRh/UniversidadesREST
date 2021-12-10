package com.ibm.academia.apirest.services;

import com.ibm.academia.apirest.models.entities.Pabellon;

public interface PabellonDAO extends GenericoDAO<Pabellon> {
	
	//public Iterable<Pabellon> findPabellonesByLocalidad(String localidad);
	
	public Iterable<Pabellon> findPabellonByNombre(String nombre);
	
	public Pabellon actualizar(Pabellon pabellonEncontrado, Pabellon pabellon);

}
