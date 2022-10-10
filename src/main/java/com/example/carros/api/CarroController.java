package com.example.carros.api;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;
import com.example.carros.api.domain.dto.CarroDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Api(value = "CarroController")
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
	public ResponseEntity insert(@RequestBody Carro carro) {

		try {
			CarroDTO result = service.save(carro);
			URI location = getUri(result.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception e) {

			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity updateById(@PathVariable Long id, @RequestBody Carro carro) {

		carro.setId(id);
		CarroDTO result = service.updateById(carro, id);

		return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable Long id) {

		return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}