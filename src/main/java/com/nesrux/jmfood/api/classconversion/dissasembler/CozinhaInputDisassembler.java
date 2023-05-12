package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaOutputDto;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomaminObject(CozinhaOutputDto outputDto) {
		return modelMapper.map(outputDto, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaOutputDto outputDto, Cozinha cozinha) {
		modelMapper.map(outputDto, cozinha);
	}

}
