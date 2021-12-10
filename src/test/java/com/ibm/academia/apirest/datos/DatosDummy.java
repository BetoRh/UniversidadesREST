package com.ibm.academia.apirest.datos;

import java.math.BigDecimal;

import com.ibm.academia.apirest.enums.Pizarron;
import com.ibm.academia.apirest.enums.TipoEmpleado;
import com.ibm.academia.apirest.models.entities.Alumno;
import com.ibm.academia.apirest.models.entities.Aula;
import com.ibm.academia.apirest.models.entities.Carrera;
import com.ibm.academia.apirest.models.entities.Direccion;
import com.ibm.academia.apirest.models.entities.Empleado;
import com.ibm.academia.apirest.models.entities.Pabellon;
import com.ibm.academia.apirest.models.entities.Persona;
import com.ibm.academia.apirest.models.entities.Profesor;

public class DatosDummy {

	public static Carrera carrera01() {
		return new Carrera(null, "Ingenieria en Sistemas", 50, 5);
	}

	public static Carrera carrera02() {
		return new Carrera(null, "Licenciatura en Sistemas", 45, 4);
	}

	public static Carrera carrera03() {
		return new Carrera(null, "Ingenieria Industrial", 60, 5);
	}

	public static Persona empleado01() {

		return new Empleado(null, "Fernando", "Lopez", "255501", new Direccion(), new BigDecimal("45285"),
				TipoEmpleado.ADMINISTRATIVO);
	}

	public static Persona empleado02() {

		return new Empleado(null, "Armando", "Reyes", "255502", new Direccion(), new BigDecimal("7859"),
				TipoEmpleado.ADMINISTRATIVO);
	}

	public static Persona empleado03() {

		return new Empleado(null, "Jose", "Reyes", "255503", new Direccion(), new BigDecimal("48591"),
				TipoEmpleado.MANTENIMIENTO);
	}

	public static Persona alumno01() {

		return new Alumno(null, "Alberto", "Gomez", "323201", new Direccion());
	}

	public static Persona alumno02() {

		return new Alumno(null, "Maria", "Alvarado", "323202", new Direccion());
	}

	public static Persona alumno03() {

		return new Alumno(null, "Rosa", "Rosas", "323203", new Direccion());
	}

	public static Persona profesor01() {

		return new Profesor(null, "Roberto", "Rivas", "320101", new Direccion(), new BigDecimal("789512"));
	}

	public static Persona profesor02() {

		return new Profesor(null, "Guadalupe", "Rivas", "320102", new Direccion(), new BigDecimal("14785"));

	}

	public static Persona profesor03() {

		return new Profesor(null, "Yazmin", "Alvarez", "320103", new Direccion(), new BigDecimal("326598"));
	}

	public static Aula aula01() {

		return new Aula(null, 1, "125.5", 35, Pizarron.PIZARRA_BLANCA);
	}

	public static Aula aula02() {

		return new Aula(null, 1, "458", 25, Pizarron.PIZARRA_BLANCA);
	}

	public static Aula aula03() {

		return new Aula(null, 3, "253.6", 40, Pizarron.PIZARRA_TIZA);
	}
	
	public static Pabellon pabellon01() {
		
		return new Pabellon(null, 145.5,"Compras", new Direccion());
		
	}
	
public static Pabellon pabellon02() {
		
		return new Pabellon(null, 185.6,"Compras", new Direccion());
		
	}

public static Pabellon pabellon03() {
	
	return new Pabellon(null, 170.6,"Administracion", new Direccion());
	
}

}
