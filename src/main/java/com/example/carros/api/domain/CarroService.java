package com.example.carros.api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository repository;
	
	public Iterable<Carro> getCarros(){
		return repository.findAll();
	}
	
	public List<Carro> getCarrosFake(){
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro(1L,"Fusca"));
		carros.add(new Carro(2L,"Brasilia"));
		carros.add(new Carro(3L,"Chevette"));
		
		return carros;
	}

	public Optional<Carro> getCarroById(Long id) {
		return repository.findById(id);
	}

}
