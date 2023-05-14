package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.estado.EstadoInputDto;
import com.nesrux.jmfood.domain.model.endereco.Estado;

@Component
public class EstadoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoInputDto inputDto) {
		return modelMapper.map(inputDto, Estado.class);
	}

	public void copyTodomainObject(EstadoInputDto inputDto, Estado estado) {
		modelMapper.map(inputDto, estado);
	}
}
