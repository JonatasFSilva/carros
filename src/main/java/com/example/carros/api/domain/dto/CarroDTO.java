package com.example.carros.api.domain.dto;

import org.modelmapper.ModelMapper;

import com.example.carros.api.domain.Carro;

import lombok.Data;

@Data
public class CarroDTO {

	private Long id;
	private String nome;
	private String tipo;
	
	public CarroDTO() {}

	public static CarroDTO create(Carro carro){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(carro, CarroDTO.class);
	}

}
