package com.nesrux.jmfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.CozinhaOutputDTO;
import com.nesrux.jmfood.api.model.dto.output.RestauranteOutputDTO;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteModelAssembler {
	public List<RestauranteOutputDTO> toCollectionDto(List<Restaurante> restaurantes) {
		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
	}

	public RestauranteOutputDTO toModel(Restaurante restaurante) {
		CozinhaOutputDTO cozinhaDTO = new CozinhaOutputDTO();
		cozinhaDTO.setId(restaurante.getCozinha().getId());
		cozinhaDTO.setNome(restaurante.getCozinha().getNome());

		RestauranteOutputDTO restauranteDTO = new RestauranteOutputDTO();
		restauranteDTO.setId(restaurante.getId());
		restauranteDTO.setNome(restaurante.getNome());
		restauranteDTO.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteDTO.setCozinha(cozinhaDTO);
		return restauranteDTO;
	}



}
