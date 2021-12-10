package com.ibm.academia.apirest.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




//@Configuration
//@EnableSwagger2
public class SpringFoxSwagger {
	/*@Bean
    public Docket getDocket()
	{
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ibm.academia.apirest.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() 
    {
        return new ApiInfo(
                "Universidad Backend API",
                "API para el manejo de Universidad",
                "V2",
                "Terminos del servicio",
                new Contact("Alberto Rios", "www.google.com", "arios251290@gmail.com"),
                "Licencia de API", "API licencia url", Collections.emptyList()
        );
    } */
}