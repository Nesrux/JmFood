package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoOutputDto;
import com.nesrux.jmfood.domain.model.endereco.Estado;

public class EstadoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	
	public Estado toDomainObject(EstadoOutputDto outputDto) {
		return modelMapper.map(outputDto, Estado.class);
	}
	
	public void copyTodomainObject(EstadoOutputDto outputDto, Estado estado) {
		modelMapper.map(outputDto, estado);
	}
}
