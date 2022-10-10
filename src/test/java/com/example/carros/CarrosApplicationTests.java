package com.example.carros;

import com.example.carros.api.domain.CarroService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	void contextLoads() {
	}

}
