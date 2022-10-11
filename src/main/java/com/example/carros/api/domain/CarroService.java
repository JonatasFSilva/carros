package com.example.carros.api.domain;

import com.example.carros.api.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	public CarroDTO save(Carro carro) {
		Assert.isNull(carro.getId(), "Não foi possivel atualizar o registro");
		return CarroDTO.create(repository.save(carro));
	}
	

	public void delete(Long id) {
		repository.deleteById(id);
		
		/*if (getCarroById(id).isPresent()) {
			repository.deleteById(id);
			return true;
		}
		return false;*/
	}

	public CarroDTO updateById(Carro carro, Long id) {
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
			
			return CarroDTO.create(db);
		}else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	
	}
}
