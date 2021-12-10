package com.ibm.academia.apirest.services;



import com.ibm.academia.apirest.models.entities.Carrera;

public interface CarreraDAO extends GenericoDAO<Carrera> {
	//@Query("select c from Carrera c where c.cantidadAnios = ?1")
		public Iterable<Carrera>findByCantidadAnios(Integer cantidadAnios);
		
		//@Query("select c from Carrera c where c.nombre like %?1%")
		public Iterable<Carrera> findCarrerasByNombreContains(String nombre);
		
		//@Query("select c from Carrera c where upper(c.nombre) upper(like %?1%"))
		public Iterable<Carrera> findCarrerasByNombreContainsIgnoreCase(String nombre);
		
		//@Query("select c from Carrera c where c.cantidadAnios > ?1")
		public Iterable<Carrera> findCarrerasByCantidadAniosAfter(Integer cantidadAnios);
		
		public Iterable<Carrera> buscarCarrerasPorProfesorNombreYApellido(String nombre, String apellido);
		
		public Carrera actualizar(Carrera carreraEncontrada, Carrera carrera);
	
}
