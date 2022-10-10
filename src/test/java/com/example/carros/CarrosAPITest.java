package com.example.carros;

import com.example.carros.api.domain.Carro;
import com.example.carros.api.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarrosAPITest {
    @Autowired
    protected TestRestTemplate rest;

    private ResponseEntity<CarroDTO> getCarro(String url){
        return rest.getForEntity(url, CarroDTO.class);
    }

    private ResponseEntity<List<CarroDTO>> getCarros(String url){
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDTO>>() {
        });
    }
    @Test
    public void listCarrosTest(){
        List<CarroDTO> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30,carros.size());
    }

    @Test
    public void listCarrosByTipoTest(){
        assertEquals(10,getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10,getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10,getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT,getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void saveTest(){
        Carro carro = new Carro();
        carro.setNome("");
        carro.setTipo("");

        //INSERT
        ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);
    }
}
