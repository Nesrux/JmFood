package com.nesrux.jmfood.api.v2.classconversion.dissassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v2.model.input.cozinha.CozinhaInputDtoV2;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaInputDisassemblerV2 {
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainnObject(CozinhaInputDtoV2 inputDto) {
		return modelMapper.map(inputDto, Cozinha.class);
	}

	public void copyToDomainObject(CozinhaInputDtoV2 inputDto, Cozinha cozinha) {
		modelMapper.map(inputDto, cozinha);
	}

}
