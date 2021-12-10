package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.models.entities.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {
	
	//@Query("select p from Pabellon p where p.(Direccion.localidad) = ?1")
	//public Iterable<Pabellon> findPabellonesByLocalidad(String localidad);
	
	@Query("select p from Pabellon p where p.nombre = ?1")
	public Iterable<Pabellon> findPabellonByNombre(String nombre);

}
