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
    public void getOkTest(){
        ResponseEntity<CarroDTO> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(),HttpStatus.OK);

        CarroDTO result = response.getBody();
        assertEquals("Ferrari FF",result.getNome());
    }
    @Test
    public void getNotFoundTest(){
        ResponseEntity response = getCarro("/api/v1/carros/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveCarroTest(){
        Carro carro = new Carro();
        carro.setNome("Porshe");
        carro.setTipo("esportivos");

        //INSERT
        ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);

        //VERIFICA SE CRIOU
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //BUSCA O OBJETO
        String location = response.getHeaders().get("location").get(0);
        CarroDTO carroDTO = getCarro(location).getBody();

        assertNotNull(carroDTO);
        assertEquals("Porshe", carroDTO.getNome());
        assertEquals("esportivos", carroDTO.getTipo());

        //DELETAR O OBJETO
        rest.delete(location);

        //VERIFICAR SE DELETOU
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }
}
