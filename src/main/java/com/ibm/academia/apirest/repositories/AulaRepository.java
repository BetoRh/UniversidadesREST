package com.ibm.academia.apirest.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.enums.Pizarron;

@Repository
public interface AulaRepository extends CrudRepository<Aula, Integer>{
	
	@Query(value = "select id,tipo_pizarron from universidad.aulas  where tipo_pizarron = ?1", nativeQuery = true)
	public Iterable<Aula> buscarAulaPorTipoPizzarra(String pizarron);
	
	@Query(value = "select a.* from universidad.aulas a inner join universidad.pabellones ON pabellones.id = a.pabellon_id where pabellones.nombre =:nombre ", nativeQuery = true)
	public Iterable<Aula> buscarAulasPorNombrePabellon(@Param("nombre") String nombre);
	
	@Query(value = "select numero_aula,tipo_pizarron from universidad.aulas where numero_aula =:numero_aula ", nativeQuery = true)
	public Iterable<Aula> buscarAulaporNumero(@Param("numero_aula") Integer numero_aula);

}
