package com.example.carros.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/")//DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class IndexController {
	
	@GetMapping //METODO PARA TRAZER ALGO
	public String get() {
		return "Get Spring Boot!!!";
	}
	
	@GetMapping("/login") // GET COM PASSAGEM DE PARAMETROS PELA URL **** NAO FAZER ISSO ****
	public String login(
			@RequestParam("login") String login,
			@RequestParam("senha") String senha) {
		return "Login: " + login + " Senha: " + senha;
		
		/*
		 * exemplo de como colocar os dados na URL
		 * localhost:8080/login?login=teste&senha=123
		 * 
		 * */
	}
	
	@GetMapping("/login/v2/{login}/senha/{senha}")
	public String login2(@PathVariable String login,
			@PathVariable String senha) 
	{return "Login: " + login + " Senha: " + senha;
	
	}
	
	@GetMapping("/carros/{id}")
	public String getCarroById(@PathVariable Long id) {
		return  "Carro by id: " + id;
	}
	
	@GetMapping("/carro/tipo/{tipo}")
	public String getCarrosByTipo(@PathVariable String tipo) {
		return "Lista de Carros Tipo: " + tipo;
	}
	
	
}
