package com.ibm.academia.apirest.datos;

import com.ibm.academia.apirest.entities.Carrera;

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
}
