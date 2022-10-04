package com.example.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/")//DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class IndexController {
	
	@GetMapping
	public String hello() {
		return "Hello Spring Boot!!!";
	}

}
