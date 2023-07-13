package com.nesrux.jmfood.api.v2.classconversion.dissassembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v2.model.input.cidade.CidadeInputDtoV2;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.endereco.Estado;

@Component
public class CidadeInputDisassemblerV2 {
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputDtoV2 inputDto) {
		return modelMapper.map(inputDto, Cidade.class);
	}

	public void copyToDomainObject(CidadeInputDtoV2 inputDto, Cidade cidade) {
		cidade.setEstado(new Estado());

		modelMapper.map(inputDto, cidade);
	}

}
