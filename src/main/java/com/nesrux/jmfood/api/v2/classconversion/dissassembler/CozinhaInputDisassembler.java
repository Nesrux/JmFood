package com.nesrux.jmfood.api.v2.classconversion.dissassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.model.dto.input.cozinha.CozinhaInputDto;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainnObject(CozinhaInputDto inputDto) {
		return modelMapper.map(inputDto, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInputDto inputDto, Cozinha cozinha) {
		modelMapper.map(inputDto, cozinha);
	}

}
