package com.example.carros;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	@DisplayName("Deve in inserir um objeto carro na tabela")
	public void insertCarroTest(){

		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivo");

		service.save(carro);
	}

}
