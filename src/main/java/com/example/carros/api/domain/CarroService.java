package com.example.carros.api.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.example.carros.api.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository repository;

	// TRAZ UM LISTA DE OBJETOS
	public List<CarroDTO> getCarros() {

		return repository.findAll().stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}

	// TRAZ UM OBJETO FILTRADO
	public Optional<CarroDTO> getCarroById(Long id) {
		return repository.findById(id).map(CarroDTO::create);
	}

	// TRAZ UM LISTA DE OBJETOS FILTRADO
	public List<CarroDTO> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
	}

	public Carro save(Carro carro) {
		return repository.save(carro);
	}
	

	public void delete(Long id) {
		
		if (getCarroById(id).isPresent()) {
			repository.deleteById(id);
		}
	}

	public Carro updateById(Carro carro, Long id) {
		Assert.notNull(id, "Não foi possivel atualizar o registro");
		Optional<Carro> optional = repository.findById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			
			//COPIA AS PROPIEDADES
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//ATUALIZA O OBJETO
			repository.save(db);
			
			return db;
		}else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	
	}
}
