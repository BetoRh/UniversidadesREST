package com.ibm.academia.apirest.models.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarreraDTO {
	
	
	private Integer id;
	
	@NotNull(message = "No puede ser vacio")
	@NotEmpty(message = "No puede ser vacio")
	@Size(min = 5, max = 80)
	private String nombre;
	
	@Positive(message = "Debe de ser mayor a 0")
	private Integer cantidadMaterias;
	
	@Positive(message = "Debe de ser mayor a 0")
	private Integer cantidadAnios;

}
