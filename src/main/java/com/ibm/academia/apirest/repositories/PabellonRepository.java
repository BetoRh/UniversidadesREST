package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Pabellon;

@Repository
public interface PabellonRepository extends CrudRepository<Pabellon, Integer> {
	
	//@Query("select p from Pabellon p where p.localidad = ?1")
	//public Iterable<Pabellon> findPabellonesByLocalidad(String localidad);
	
	@Query(value = "select nombre from universidad.pabellones where nombre = ?1", nativeQuery = true)
	public Iterable<Pabellon> findPabellonByNombre(String nombre);

}
