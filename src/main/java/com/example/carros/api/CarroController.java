package com.example.carros.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.example.carros.api.domain.dto.CarroDTO;

@RestController // TRANSFORMA ESSA CLASSE EM UM WEB SERVICE REST
@RequestMapping("/api/v1/carros") // DEFINE PARA ONDE O WEB SERVICE ESTA MAPEADO
public class CarroController {

	@Autowired
	private CarroService service;

	@GetMapping // VERBO HHTP PARA TRAZER UMA LISTA DE OBJETOS SEM FILTRO
	public ResponseEntity getAll() {
		return ResponseEntity.ok(service.getCarros());
	}

	@GetMapping("/{id}") // VERBO HHTP PARA TRAZER UM OBJETO FILTRADO PELO SEU ID
	public ResponseEntity getById(@PathVariable Long id) {
		Optional<CarroDTO> carro = service.getCarroById(id);
		// STATUS OK (200)
		// STATUS NO_CONTENT (204)
		return carro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/tipo/{tipo}") // VERBO HHTP PARA TRAZER UMA LISTA FILTRADA PELO SEU TIPO
	public ResponseEntity getByTipo(@PathVariable String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		// SE A LISTA CARROS ESTIVER VAZIA ENTAO (?) RETORNA NO_CONTENT(204) CASO
		// CONTRARIO
		// (:) RETORNA OK(200)
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	@PostMapping
	public String insert(@RequestBody Carro carro) {
		Carro result = service.save(carro);

		return "Carro salvo com sucesso id: " + result.getId();
	}

	@PutMapping("/{id}")
	public CarroDTO updateById(@PathVariable Long id, @RequestBody Carro carro) {
		return service.updateById(carro, id);
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id) {

		service.delete(id);

		return "Carro deletado com sucesso id: " + id;
	}

}