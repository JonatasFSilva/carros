package com.example.carros.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/api/v1/carros")//DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class CarroController {
	public CarroService service = new CarroService();
	
	@GetMapping //METODO PARA TRAZER ALGO
	public List<Carro> getAll() {
		return service.getCarros();
	}
	
}
