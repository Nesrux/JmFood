package com.nesrux.jmfood.api.v1.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.model.dto.input.cidade.CidadeInputDto;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.model.endereco.Estado;

@Component
public class CidadeInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputDto inputDto) {
		return modelMapper.map(inputDto, Cidade.class);
	}

	public void copyToDomainObject(CidadeInputDto inputDto, Cidade cidade) {
		cidade.setEstado(new Estado());

		modelMapper.map(inputDto, cidade);
	}

}
