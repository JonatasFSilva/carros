package com.example.carros;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.CarroService;
import com.example.carros.api.domain.dto.CarroDTO;
import com.example.carros.api.exception.ObjectNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
		result = service.getCarroById(id);
		assertNotNull(result);


		assertEquals("Ferrari", result.getNome());
		assertEquals("esportivo", result.getTipo());

		//DELETAR O OBJETO
		service.delete(id);

		//VERIFICAR SE O OBJETO FOI DELETADO

		try {
			assertNull(service.getCarroById(id));
			fail("O Carro não foi excluido");
		}catch (ObjectNotFoundException error){
			// OK
		}

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
		CarroDTO carros = service.getCarroById(11L);

		assertNotNull(carros);


		assertEquals("Ferrari FF", carros.getNome());
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
