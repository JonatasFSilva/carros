package com.example.carros.api;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;
import com.example.carros.api.domain.dto.CarroDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
		CarroDTO carro = service.getCarroById(id);
		// STATUS OK (200)
		// STATUS NO_CONTENT (204)
		return ResponseEntity.ok(carro);
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
	@Secured({"ROLE_ADMIN"})
	public ResponseEntity insert(@RequestBody Carro carro) {

			CarroDTO result = service.save(carro);
			URI location = getUri(result.getId());
			return ResponseEntity.created(location).build();
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

		service.delete(id);
		return ResponseEntity.ok().build();
		//return service.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
	}

}