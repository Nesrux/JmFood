package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.RestauranteInputDTO;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteInputDisassembler{
	@Autowired
	private ModelMapper modelMapper;
	
	public Restaurante toDomainObject(RestauranteInputDTO inputDTO) {
		return modelMapper.map(inputDTO, Restaurante.class);
	
	
	}

}
