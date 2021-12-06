package com.ibm.academia.apirest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.ibm.academia.apirest.entities.Alumno;
import com.ibm.academia.apirest.entities.Aula;
import com.ibm.academia.apirest.entities.Carrera;
import com.ibm.academia.apirest.entities.Direccion;
import com.ibm.academia.apirest.entities.Empleado;
import com.ibm.academia.apirest.entities.Pabellon;
import com.ibm.academia.apirest.entities.Persona;
import com.ibm.academia.apirest.entities.Profesor;
import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.services.AlumnoDAO;
import com.ibm.academia.apirest.services.AulaDAO;
import com.ibm.academia.apirest.services.CarreraDAO;
import com.ibm.academia.apirest.services.CarreraDAOImpl;
import com.ibm.academia.apirest.services.EmpleadoDAO;
import com.ibm.academia.apirest.services.PabellonDAO;
import com.ibm.academia.apirest.services.PersonaDAO;
import com.ibm.academia.apirest.services.ProfesorDAO;


//@ComponentScan({"com.ibm.academia.apirest.repositories","com.ibm.academia.apirest.services"})
@Component
public class Comandos implements CommandLineRunner {
	
	//@Autowired
	//private CarreraDAO carreraDAO;
	
	//@Autowired
	//private AlumnoDAO alumnoDAO;
	
	//@Autowired
	//private ProfesorDAO profesorDAO;
	
	@Autowired
	private PabellonDAO pabellonDAO;
	
	//@Autowired
	//private AulaDAO aulaDAO;
	
	/*@Autowired
	@Qualifier("repositorioProfesores")
	private PersonaDAO personaDAO;*/
	
	//@Autowired
	//private EmpleadoDAO empleadoDAO;
	
	//@Autowired
	//private AulaDAO aulaDAO;

	@Override
	public void run(String... args) throws Exception {
		
		
		//List<Carrera> carrerasPorAnios = (List<Carrera>) carreraDAO.findCarrerasByCantidadAniosAfter(2);
		//carrerasPorAnios.forEach(System.out::println);
		
		//Repositorios Profesores
		
		//List<Persona> profesoresCarreras = (List<Persona>) profesorDAO.findProfesoresByCarrera("Derecho");
		//profesoresCarreras.forEach(a -> System.out.println(a));
		
		//Repositorio Empleados
		
		//Iterable<Persona> tipoEmpleados = empleadoDAO.findEmpleadoByTipoEmpleado(TipoEmpleado.ADMINISTRATIVO);
		//tipoEmpleados.forEach(a -> System.out.println(a));
		
		//Repositorio Carreras
		
		//List<Carrera> carrerasNombre = (List<Carrera>) carreraDAO.buscarCarrerasPorProfesorNombreYApellido("Jose", "Alvarez");
		//carrerasNombre.forEach(c ->System.out.println(c));
		
		//Repositorio Aulas
		
		//List<Aula> aulaPizarra = (List<Aula>) aulaDAO.buscarAulaPorTipoPizzarra("PIZARRA_TIZA");
		//aulaPizarra.forEach(a -> System.out.println(a));
		
		//List<Aula> aulasPabellon = (List<Aula>) aulaDAO.buscarAulasPorNombrePabellon("Redes");
		//aulasPabellon.forEach(a -> System.out.println(a));
		
		
		//List<Aula> aulaNumero = (List<Aula>) aulaDAO.buscarAulaporNumero(6);
		//aulaNumero.forEach(a -> System.out.println(a));
		
		//Repositorios Pabellones
		
		//List<Pabellon> localidadPabellon = (List<Pabellon>) pabellonDAO.findPabellonesByLocalidad("Rubirosa");
		//localidadPabellon.forEach(l -> System.out.println(l));
		
		 Iterable<Pabellon> pabellonNombre = pabellonDAO.findPabellonByNombre("Redes");
		 System.out.println(pabellonNombre.toString());
		
			
		}
		
	}

		
	


