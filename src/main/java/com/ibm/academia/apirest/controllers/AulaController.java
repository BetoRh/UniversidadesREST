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
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.services.AulaDAO;

@RestController
@RequestMapping("/aula")
public class AulaController {
	
	@Autowired
	private AulaDAO aulaDAO;
	
	/**
	 * Endpoint para mostrar Lista de Aulas
	 * @return Retorna lista de aulas en bd
	 * @author RAJA 10/12/2021
	 */
	
	@GetMapping("/lista/aulas")
	public List<Aula> buscarTodas(){
		
		List<Aula> aulas = (List<Aula>) aulaDAO.buscarTodos();
		
		if(aulas.isEmpty())
			throw new BadRequestException("No existen aulas");
		return aulas;
	}
	
	/**
	 * Endpoint para Buscar Aula por Id
	 * @param aulaId Parametro para buscar Aula
	 * @return Retorna el registro buscado por id
	 * @BadRequestException En caso de que no exista registro con id
	 */
	
	@GetMapping("/id/{aulaId}")
	public Aula buscarAulaPorId(@PathVariable Integer aulaId) {
		
		Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);
		if(!oAula.isPresent())
			throw new BadRequestException(String.format("La aula con ID: %d no existe", aulaId));
			
		return oAula.get();
	}
	
	/**
	 * Endpoint para inserta nueva Aula
	 * @param aula Parametro para insertar nuevo registro
	 * @param result Parametro para atrapar errores
	 * @return Retorna el registro insertado en bd
	 * @author RAJA 09/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> guardarAula(@Valid @RequestBody Aula aula, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "'  " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
			
		}
		
		Aula aulaGuardada = aulaDAO.guardar(aula);
		
		return new ResponseEntity<Aula>(aulaGuardada, HttpStatus.CREATED);
		
	}
	
	/**
	 * Endpoint para Actualizar Aula
	 * @param aulaId Parametro para buscar aula
	 * @param aula Parametro para actualizar aula
	 * @return Retorna informacion actualizada de aula
	 * @NotFoundException En caso de que no se encuentre aula con id
	 * @author RAJA 10/12/2021
	 */
	
	@PutMapping("/upd/aulaId/{aulaId}")
	  public ResponseEntity<?> actualizarAula(@PathVariable Integer aulaId, @RequestBody Aula aula){
		 
	    Optional<Aula> oAula = aulaDAO.buscarPorId(aulaId);
	    
	    if(!oAula.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("El aula con ID: %d no existe", aulaId));
	    
	    Aula aulaActualizada = aulaDAO.actualizar(oAula.get(), aula); 
	    
	    return new ResponseEntity<Aula>(aulaActualizada, HttpStatus.OK); 
	  }
	/**
	 * Endpoint para Eliminar Aula
	 * @param aulaId Parametro para Buscar Aula
	 * @return Retorna valor id eliminado
	 * @NotFoundException En caso deque no exista el id a eliminar
	 * @author RAJA 09/12/2021
	 */
	
	 @DeleteMapping("/aulaId/{aulaId}")
	  public ResponseEntity<?> eliminarAula(@PathVariable Integer aulaId){
	    Map<String, Object> respuesta = new HashMap<String, Object>();
	    
	    Optional<Aula> aula = aulaDAO.buscarPorId(aulaId);
	    
	    if(!aula.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("El aula con ID: %d no existe", aulaId));
	    
	    aulaDAO.eliminarPorId(aulaId);
	    respuesta.put("OK", "Aula ID: " + aulaId + " eliminada exitosamente");
	    return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	  }

}
