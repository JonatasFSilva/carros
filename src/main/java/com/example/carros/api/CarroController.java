package com.example.carros.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/api/v1/carros") // DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping // VERBO HHTP PARA TRAZER UMA LISTA DE OBJETOS SEM FILTRO
	public Iterable<Carro> getAll() {
		return service.getCarros();
	}

	@GetMapping("/{id}") // VERBO HHTP PARA TRAZER UM OBJETO FILTRADO PELO SEU ID
	public Optional<Carro> getById(@PathVariable Long id) {
		return service.getCarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}") // VERBO HHTP PARA TRAZER UMA LISTA FILTRADA PELO SEU TIPO
	public Iterable<Carro> getByTipo(@PathVariable String tipo){
		return service.getCarroByTipo(tipo);
	}
	
	@PostMapping
	public String insert(@RequestBody Carro carro) {
		 Carro result = service.save(carro);
		 
		 return "Carro salvo com sucesso id: " + result.getId();
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, @RequestBody Carro carro ) {
		Carro result = service.update(carro, id);
		
		return "Carro atualizado com sucesso id: " + result.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso id: " + id;
	}

}