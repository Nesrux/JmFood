package com.nesrux.jmfood.api.v1.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.model.dto.input.restaurante.RestauranteInputDto;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInputDto inputDTO) {
		return modelMapper.map(inputDTO, Restaurante.class);
	}

	public void copyTodomainObject(RestauranteInputDto restauranteInputDTO, Restaurante restaurante) {
		// Para evitar a exception de trocar o id de uma cozinha, foi instanciado uma
		// nova cozinhas na linha abaixo
		restaurante.setCozinha(new Cozinha());
		
		if(restaurante.getEndereco() != null) {
			restaurante.getEndereco().setCidade(new Cidade());
		}

		modelMapper.map(restauranteInputDTO, restaurante);
	}
}
