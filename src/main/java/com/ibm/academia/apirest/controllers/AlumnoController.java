package com.ibm.academia.apirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
	  
	  @Autowired
	  @Qualifier("alumnoDAOImpl") 
	  private PersonaDAO alumnoDAO;
	  
	  @Autowired
	  private CarreraDAO carreraDAO;
	  
	 /**
	  * Endopint para ingresar nuevos registros de Alumnos en bd
	  * @param alumno Parametro que se asigna para crear un nuevo Alumno
	  * @return El registro del nuevo alumno insertado en bd
	  * @author JARA 09/12/2021
	  */
	  
	  @PostMapping
	  public ResponseEntity<?> crearAlumno(@RequestBody Persona alumno){
	    
	    Persona alumnoGuardado = alumnoDAO.guardar(alumno);
	    return new ResponseEntity<Persona>(alumnoGuardado, HttpStatus.CREATED);
	  }
	  /**
	   * Endpoint para mostrar la lista de alumnos en bd
	   * @return Regresa la lista de alumnas en bd
	   * @NotFoundException En caso de que no se encuentren alumnos en bd
	   * @author JARA 09/12/2021
	   */
	 
	  
	  @GetMapping("/alumnos/lista")
	  public ResponseEntity<?> obtenerTodos() {
	    
	    List<Persona> alumnos = (List<Persona>) alumnoDAO.buscarTodos();
	    
	    if(alumnos.isEmpty())
	      throw new NotFoundException("No existen alumnos");
	    return new ResponseEntity<List<Persona>>(alumnos, HttpStatus.OK);
	  }
	  /**
	   * Enpoint para buscar alumnos por id
	   * @param alumnoId Parametro para buscar el Alumno
	   * @return Regresa el alumno buscado 
	   * @NotFoundException En caso de que el alumno con id no exista
	   * @author RIAJ 09/12/2021
	   */
	
	  @GetMapping("/alumnoId/{alumnoId}")
	    public ResponseEntity<?> obtenerAlumnoPorId(@PathVariable Integer alumnoId)
	    {
	        Optional<Persona> oAlumno = alumnoDAO.buscarPorId(alumnoId);
	        
	        if(!oAlumno.isPresent()) 
	            throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
	        
	        return new ResponseEntity<Persona>(oAlumno.get(), HttpStatus.OK);
	    }
	  /**
	   * Endpoint para actualizar Alumnos en bd
	   * @param alumnoId Parametro para buscar el Alumno
	   * @param alumno Tipo de persona que se va a actualizar
	   * @return Retorna a la persona ya actualizada
	   * @NotFoundException En caso de que el alumno con id no exista
	   * @author RAJA 09/12/2021
	   */
	 
	  @PutMapping("/upd/alumnoId/{alumnoId}")
	  public ResponseEntity<?> actualizarAlumno(@PathVariable Integer alumnoId, @RequestBody Persona alumno) {
	    Optional<Persona> oAlumno = alumnoDAO.buscarPorId(alumnoId);
	    
	    if(!oAlumno.isPresent())
	      throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));
	    
	    Persona alumnoActualizado = ((AlumnoDAO)alumnoDAO).actualizar(oAlumno.get(), alumno);
	    return new ResponseEntity<Persona>(alumnoActualizado, HttpStatus.OK);
	  }
	  /**
	   * Endpoint para eliminar Alumnos
	   * @param alumnoId Parametro para buscar el registro
	   * @return Retorna mensaje con id de Alumno eliminado
	   * @NotFoundException En caso de que el Alumno con id no exista
	   * @author RAJA 09/12/2021
	   */
	
	  @DeleteMapping("/alumnoId/{alumnoId}")
	  public ResponseEntity<?> eliminarAlumno(@PathVariable Integer alumnoId){
	    Optional<Persona> oAlumno = alumnoDAO.buscarPorId(alumnoId);
	    
	    if(!oAlumno.isPresent())
	      throw new NotFoundException(String.format("El alumno con ID %d no existe", alumnoId));
	    
	    alumnoDAO.eliminarPorId(oAlumno.get().getId()); 
	    
	    return new ResponseEntity<String>("Alumno ID: " + alumnoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	  }
	  /**
	   * Endpoint para asignar Carrera a Alumno
	   * @param carreraId Parametro para buscar Carrera
	   * @param alumnoId Parametro para buscar Alumno
	   * @return Regresa informacion del Alumno actualizada
	   * @author RAJA 09/12/2021
	   */
	 
	  @PutMapping("/alumnoId/{alumnoId}/carrera/{carreraId}")
	  public ResponseEntity<?> asignarCarreraAlumno(@PathVariable Integer carreraId, @PathVariable Integer alumnoId) {
	    Optional<Persona> oAlumno = alumnoDAO.buscarPorId(alumnoId);
	        if(!oAlumno.isPresent()) 
	            throw new NotFoundException(String.format("Alumno con id %d no existe", alumnoId));
	        
	        Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
	        if(!oCarrera.isPresent())
	            throw new NotFoundException(String.format("Carrera con id %d no existe", carreraId));
	        
	        Persona alumno = ((AlumnoDAO)alumnoDAO).asociarCarreraAlumno(oAlumno.get(), oCarrera.get());
	        
	        return new ResponseEntity<Persona>(alumno, HttpStatus.OK);
	  }
	}


