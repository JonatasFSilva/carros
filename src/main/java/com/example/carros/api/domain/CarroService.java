package com.example.carros.api.domain;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	//TRAZ UM LISTA DE OBJETOS
	public Iterable<Carro> getCarros(){
		return repository.findAll();
	}
	
	//TRAZ UM OBJETO FILTRADO
	public Optional<Carro> getCarroById(Long id) {
		return repository.findById(id);
	}

	//TRAZ UM LISTA DE OBJETOS FILTRADO
	public Iterable<Carro> getCarroByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}

	public Carro save(Carro carro) {
		return repository.save(carro);
		
	}

	public Carro update(Carro carro, Long id){
		
		Assert.notNull(id, "Não foi possivel atualizar o registro");
		
		return getCarroById(id).
		map(db -> {
			//COPIA AS PROPIEDADES DO BANCO DE DADOS
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			
			//ATUALIZA O OBJETO NO BANCO DE DADOS
			repository.save(db);
			
			return db;
			
		}).orElseThrow(() -> new RuntimeException("Não foi possivel atualizar o registro"));		
		
		
	}
	
	/*
	public List<Carro> getCarrosFake(){
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L,"Fusca", ""));
		carros.add(new Carro(2L,"Brasilia" ,""));
		carros.add(new Carro(3L,"Chevette" ,""));
		
		return carros;
	}*/

	

}
