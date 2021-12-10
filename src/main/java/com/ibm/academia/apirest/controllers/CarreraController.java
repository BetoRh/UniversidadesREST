package com.ibm.academia.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.mapper.CarreraMapper;
import com.ibm.academia.apirest.models.dto.CarreraDTO;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.CarreraDAO;

import javassist.NotFoundException;

@RestController
@RequestMapping("/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraDAO carreraDAO;
	/**
	 * Endpoint que muestra la lista de carreras en bd
	 * @return Regresa la lista Carreras
	 * @BadRequestException En caso de de que no encuentre ninguna carrera
	 * @author JARA 08/12/2021
	 */
	@GetMapping("/lista/carreras")
	public List<Carrera> buscarTodas(){
		
		List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
		
		if(carreras.isEmpty())
			throw new BadRequestException("No existen carreras");
		return carreras;
	}
	/**
	 * Enpoint para buscar carreras por id
	 * @param carreraId Parametro solicitado para realizar la busqueda de Carrera
	 * @return Regresa la Carrera de acuerdo al id ingresado
	 * @BadRequestException En caso de que la carrera no exista
	 * @author RAJA 08/12/2021
	 */
	@GetMapping("/id/{carreraId}")
	public Carrera buscarCarreraPorId(@PathVariable Integer carreraId) {
		
		Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
		if(!oCarrera.isPresent())
			throw new BadRequestException(String.format("La carrera con ID: %d no existe", carreraId));
			
		return oCarrera.get();
	}
	/**
	 * Enpoint para realizar el registro de nuevas carreras
	 * @param carrera Parametro para ingresar la Carrera
	 * @param result 
	 * @return Regresa una nueva Carrera ingresada
	 * @author JARA 08/12/09
	 */
	@PostMapping
	public ResponseEntity<?> guardarCarrera(@Valid @RequestBody Carrera carrera, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "'  " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
			
		}
		
		Carrera carreraGuardada = carreraDAO.guardar(carrera);
		
		return new ResponseEntity<Carrera>(carreraGuardada, HttpStatus.CREATED);
		
	}
	/**
	 * Endpoint para actualizar un objeto de tipo carrera
	 * @param carreraId Parametro para buscar la carrera
	 * @param carrera Objeto con la informacion a modificar
	 * @return Retorna un objeto de tipo Carrera con la informacion actualizada
	 * @NotFoundException En caso de que falle la actualizando el objeto carrera
	 * @author JARA - 08/12/2021
	 */
	 @PutMapping("/upd/carreraId/{carreraId}")
	  public ResponseEntity<?> actualizarCarrera(@PathVariable Integer carreraId, @RequestBody Carrera carrera){
		 
	    Optional<Carrera> oCarrera = carreraDAO.buscarPorId(carreraId);
	    
	    if(!oCarrera.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
	    
	    Carrera carreraActualizada = carreraDAO.actualizar(oCarrera.get(), carrera); 
	    
	    return new ResponseEntity<Carrera>(carreraActualizada, HttpStatus.OK); 
	  }
	 
	 /**
	  * Endpoint para eliminar Carreras
	  * @param carreraId Parametro solicitado para buscar la carrrea
	  * @return Elimina el registro de la carrera
	  * @BadRequestException En caso de que la carrera no exista
	  */
	 
	 @DeleteMapping("/carreraId/{carreraId}")
	  public ResponseEntity<?> eliminarCarrera(@PathVariable Integer carreraId){
	    Map<String, Object> respuesta = new HashMap<String, Object>();
	    
	    Optional<Carrera> carrera = carreraDAO.buscarPorId(carreraId);
	    
	    if(!carrera.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("La carrera con ID: %d no existe", carreraId));
	    
	    carreraDAO.eliminarPorId(carreraId);
	    respuesta.put("OK", "Carrera ID: " + carreraId + " eliminada exitosamente");
	    return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	  }
	 
	 /**
	  * Endpoint que regresa la lista de carrera pero con solo con algunos parametros seleccionados
	  * @return Regresa una lista de carreras
	  * @author JARA 08/12/2021
	  */
	 @GetMapping("/carreras/dto")
	 public ResponseEntity<?> obtenerCarrerasDTO(){
		 
		 List<Carrera> carreras = (List<Carrera>) carreraDAO.buscarTodos();
		 
		 if(carreras.isEmpty())
			 throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException("No existen carreras en la base de datos.");
		  
		 List<CarreraDTO> listaCarreras = carreras
				 .stream()
				 .map(CarreraMapper::mapCarrera)
				 .collect(Collectors.toList());
		 
		 return new ResponseEntity<List<CarreraDTO>>(listaCarreras, HttpStatus.OK);
	 }

}
