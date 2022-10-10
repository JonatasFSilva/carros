package com.example.carros;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;
import com.example.carros.api.domain.dto.CarroDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.*;

@SpringBootTest
class CarrosServiceTests {
	@Autowired
	private CarroService service;
	@Test
	@DisplayName("Deve inserir um objeto carro na tabela")
	public void insertCarroTest(){

		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivo");

		CarroDTO result = service.save(carro);
		assertNotNull(result);

		Long id = result.getId();
		assertNotNull(id);

		// BUSCAR O OBJETO
		Optional<CarroDTO> op = service.getCarroById(id);
		assertTrue(op.isPresent());

		result = op.get();
		assertEquals("Ferrari", result.getNome());
		assertEquals("esportivo", result.getTipo());

		//DELETAR O OBJETO
		service.delete(id);

		//VERIFICAR SE O OBJETO FOI DELETADO
		assertFalse(service.getCarroById(id).isPresent());
	}
	@Test
	@DisplayName("Deve listar os carros")
	public void listAllCarrosTest(){
		List<CarroDTO> carros = service.getCarros();

		assertEquals(30, carros.size());
	}
	@Test
	@DisplayName("Deve listar carro por ID")
	public void listCarroByIDTest(){
		Optional<CarroDTO> carros = service.getCarroById(11L);

		CarroDTO result = carros.get();

		assertEquals("Ferrari FF", result.getNome());
	}
	@Test
	@DisplayName("Deve listar carros por Tipo")
	public void listCarrosByTipo(){
		assertEquals(10,service.getCarroByTipo("esportivos").size());
		assertEquals(10,service.getCarroByTipo("classicos").size());
		assertEquals(10,service.getCarroByTipo("luxo").size());
		assertEquals(0,service.getCarroByTipo("x").size());
	}
}