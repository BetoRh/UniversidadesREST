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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.academia.apirest.exceptions.BadRequestException;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.services.PabellonDAO;

@RestController
@RequestMapping("/pabellon")
public class PabellonController {
	
	@Autowired
	private PabellonDAO pabellonDAO;
	
	
	/**
	 * Endpoint para Buscar todos los pabellones bd
	 * @return Retorna la lista de los pabellones en bd
	 * @author RAJA 10/12/2021
	 */
	@GetMapping("/lista/pabellones")
	public List<Pabellon> buscarTodas(){
		
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.buscarTodos();
		
		if(pabellones.isEmpty())
			throw new BadRequestException("No existen pabellones");
		return pabellones;
	}
	
	/**
	 * Endpoint para Buscar Pabellon por Id
	 * @param pabellonId Parametro para buscar Pabellon
	 * @return Retorna el registro con id en bd
	 * @BadRequestException En caso de que no exista pabellon con id
	 * @author RAJA 10/12/2021
	 */
	
	@GetMapping("/id/{pabellonId}")
	public Pabellon buscarPabellonPorId(@PathVariable Integer pabellonId) {
		
		Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
		if(!oPabellon.isPresent())
			throw new BadRequestException(String.format("La pabellon con ID: %d no existe", pabellonId));
			
		return oPabellon.get();
	}
	/**
	 * Endpoint para insertar nuevos Pabellones en bd
	 * @param pabellon Parametro para insertar pabellon
	 * @param result Paramitro para validacion de errores
	 * @return Retorna el registro insertado en bd
	 * @author RAJA 10/12/2021
	 */
	@PostMapping
	public ResponseEntity<?> guardarPabellon(@Valid @RequestBody Pabellon pabellon, BindingResult result){
		
		Map<String, Object> validaciones = new HashMap<String, Object>();
		if(result.hasErrors()) {
			List<String> listaErrores = result.getFieldErrors()
					.stream()
					.map(errores -> "Campo: '" + errores.getField() + "'  " + errores.getDefaultMessage())
					.collect(Collectors.toList());
			validaciones.put("Lista Errores", listaErrores);
			return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
			
		}
		
	Pabellon pabellonGuardado = pabellonDAO.guardar(pabellon);
		
		return new ResponseEntity<Pabellon>(pabellonGuardado, HttpStatus.CREATED);
		
	}
	/**
	 * Endpoint para Actualizar Pabellon
	 * @param pabellonId Parametro para buscar Pabellon
	 * @param pabellon Parametro para actualizar Pabellon
	 * @return Retorna el registro con informacion actualizada
	 * @NotFoundException En caso de que el registro con id no exista en bd
	 * @author RAJA 10/12/2021
	 */
	@PutMapping("/upd/pabellonId/{pabellonId}")
	  public ResponseEntity<?> actualizarPabellon(@PathVariable Integer pabellonId, @RequestBody Pabellon pabellon){
		 
	    Optional<Pabellon> oPabellon = pabellonDAO.buscarPorId(pabellonId);
	    
	    if(!oPabellon.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("El pabellon con ID: %d no existe", pabellonId));
	    
	    Pabellon pabellonActualizado = pabellonDAO.actualizar(oPabellon.get(), pabellon); 
	    
	    return new ResponseEntity<Pabellon>(pabellonActualizado, HttpStatus.OK); 
	  }
	/**
	 * Endpoint para eliminar Pabellones de bd
	 * @param carreraId Parametro eliminar id
	 * @return Retorna id de registro elimniado
	 * @NotFoundException En caso de que el id a eliminar no exista
	 * @author RAJA 10/12/2021
	 */
	@DeleteMapping("/pabellonId/{pabellonId}")
	  public ResponseEntity<?> eliminarPabellon(@PathVariable Integer pabellonId){
	    Map<String, Object> respuesta = new HashMap<String, Object>();
	    
	    Optional<Pabellon> pabellones = pabellonDAO.buscarPorId(pabellonId);
	    
	    if(!pabellones.isPresent())
	      throw new com.ibm.academia.apirest.exceptions.handler.NotFoundException(String.format("El pabellon con ID: %d no existe", pabellonId));
	    
	    pabellonDAO.eliminarPorId(pabellonId);
	    respuesta.put("OK", "Pabellon ID: " + pabellonId + " eliminado exitosamente");
	    return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.ACCEPTED);
	  }
	

	@GetMapping("/nombre")
	public List<Pabellon> buscarPabellonPorNombre(@RequestParam String nombrePabellon) {
		
		List<Pabellon> pabellones = (List<Pabellon>) pabellonDAO.findPabellonByNombre(nombrePabellon);
		if(pabellones.isEmpty())
			throw new BadRequestException(String.format("La pabellon con ID: %d no existe", nombrePabellon));
			
		return pabellones;
	}
	 

}
