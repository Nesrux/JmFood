package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.RestauranteOutputDTO;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteOutputAssembler {
	@Autowired
	private ModelMapper modelMapper;
	
	public List<RestauranteOutputDTO> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}

	public RestauranteOutputDTO toModel(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteOutputDTO.class);
	}



}
