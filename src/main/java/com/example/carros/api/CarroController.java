package com.example.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/api/v1/carros") // DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping // METODO PARA TRAZER ALGO
	public Iterable<Carro> getAll() {
		return service.getCarros();
	}

	@GetMapping("/{id}")
	public Optional<Carro> getById(@PathVariable Long id) {
		return service.getCarroById(id);
	}

}
