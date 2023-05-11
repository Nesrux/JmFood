package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.RestauranteInputDTO;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteModelDissasembler {
	public Restaurante toDomainObject(RestauranteInputDTO inputDTO) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(inputDTO.getNome());
		restaurante.setTaxaFrete(inputDTO.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(inputDTO.getCozinha().getId());

		restaurante.setCozinha(cozinha);

		return restaurante;
	}

}
