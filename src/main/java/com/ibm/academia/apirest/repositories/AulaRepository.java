package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.models.entities.Aula;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer>{
	
	//@Query("select a from Aula a where a.tipo_pizarron = ?1")
	//public Iterable<Aula> buscarAulaPorTipoPizzarra(Pizarron pizarron);
	
	@Query(value = "select a.* from universidad.aulas a inner join universidad.pabellones ON pabellones.id = a.pabellon_id where pabellones.nombre =:nombre ", nativeQuery = true)
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre);
	
	@Query("select a from Aula a where a.numeroAula = ?1")
	public Iterable<Aula> buscarAulaporNumero(Integer numero_aula);

}
