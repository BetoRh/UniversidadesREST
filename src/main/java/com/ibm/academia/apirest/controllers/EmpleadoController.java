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

import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.exceptions.handler.NotFoundException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PersonaDAO;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	
	@Autowired
	@Qualifier("empleadoDAOImpl")
	private PersonaDAO empleadoDAO;
	
	@Autowired
	private PabellonDAO pabellonDAO;
	
	/**
	 * Endpoint para ingresar nuevos Empleados en bd
	 * @param empleado Parametro para insertar nuevo registro en bd
	 * @return Retorna informacion del nuevo registro
	 * @author RAJA 09/12/2021
	 */
	  @PostMapping
	  public ResponseEntity<?> crearEmpleado(@RequestBody Persona empleado){
	    
	    Persona empleadoGuardado = empleadoDAO.guardar(empleado);
	    return new ResponseEntity<Persona>(empleadoGuardado, HttpStatus.CREATED);
	  }
	
	  /**
	   * Endpoint para mostrar lista de Empleados
	   * @return Regresa la lista de empleado en bd
	   * @NotFoundException En caso de que no existan empleados
	   * @author RAJA 09/12/2021
	   */
	  
	  @GetMapping("/empleados/lista")
	  public ResponseEntity<?> obtenerTodos() {
	    
	    List<Persona> empleados = (List<Persona>) empleadoDAO.buscarTodos();
	    
	    if(empleados.isEmpty())
	      throw new NotFoundException("No existen empleados");
	    return new ResponseEntity<List<Persona>>(empleados, HttpStatus.OK);
	  }
	  
	  /**
	   * Endpoint para Buscar empleados por id
	   * @param empleadoId Parametro para buscar Empleado
	   * @return Regresa el registro buscado por id
	   * @NotFoundException En caso de que el registro con id no exista
	   * @author RAJA 09/12/2021
	   */
	  
	  @GetMapping("/empleadoId/{empleadoId}")
	    public ResponseEntity<?> obtenerEmpleadoPorId(@PathVariable Integer empleadoId)
	    {
	        Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);
	        
	        if(!oEmpleado.isPresent()) 
	            throw new NotFoundException(String.format("Empleado con id %d no existe", empleadoId));
	        
	        return new ResponseEntity<Persona>(oEmpleado.get(), HttpStatus.OK);
	    }
	  
	  /**
	   * Endpoint para actualizar registros en bd
	   * @param empleadoId Parametro para buscar empleado
	   * @param empleado Parametro para actualizar empleado
	   * @return Regresa el registro con informacion actualizada
	   * @NotFoundException En caso de que el registro con id no exista
	   * @author RAJA 09/12/2021
	   */
	  
	  @PutMapping("/upd/empleadoId/{empleadoId}")
	  public ResponseEntity<?> actualizarEmpleado(@PathVariable Integer empleadoId, @RequestBody Persona empleado) {
	    Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);
	    
	    if(!oEmpleado.isPresent())
	      throw new NotFoundException(String.format("El empleado con ID %d no existe", empleadoId));
	    
	    Persona empleadoActualizado = ((EmpleadoDAO)empleadoDAO).actualizar(oEmpleado.get(), empleado);
	    return new ResponseEntity<Persona>(empleadoActualizado, HttpStatus.OK);
	  }
	  
	  /**
	   * Endpoint para Eliminar empleados 
	   * @param empleadoId Parametro para buscar el registro
	   * @return Regresa id de empleado eliminado
	   * @NotFoundException En caso de que el registro con id no exista
	   * @author RAJA 09/12/2021
	   */
	  @DeleteMapping("/empleadoId/{empleadoId}")
	  public ResponseEntity<?> eliminarEmpleado(@PathVariable Integer empleadoId){
	    Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);
	    
	    if(!oEmpleado.isPresent())
	      throw new NotFoundException(String.format("El alumno con ID %d no existe", empleadoId));
	    
	    empleadoDAO.eliminarPorId(oEmpleado.get().getId()); 
	    
	    return new ResponseEntity<String>("Alumno ID: " + empleadoId + " se elimino satisfactoriamente",  HttpStatus.NO_CONTENT);
	  }
	  
	  @PutMapping("/empleadoId/{empleadoId}/pabellon/{pabellonId}")
	  public ResponseEntity<?> asignarPabellonEmpleado(@PathVariable Integer pabellonId, @PathVariable Integer empleadoId) {
	    Optional<Persona> oEmpleado = empleadoDAO.buscarPorId(empleadoId);
	        if(!oEmpleado.isPresent()) 
	            throw new NotFoundException(String.format("Empleado con id %d no existe", empleadoId));
	        
	        Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
	        if(!oPabellon.isPresent())
	            throw new NotFoundException(String.format("Pabellon con id %d no existe", pabellonId));
	        
	        Persona empleado = ((EmpleadoDAO)empleadoDAO).asociarPabellonEmpleado(oEmpleado.get(), oPabellon.get());
	        
	        return new ResponseEntity<Persona>(empleado, HttpStatus.OK);
	  }
	  
	

}
