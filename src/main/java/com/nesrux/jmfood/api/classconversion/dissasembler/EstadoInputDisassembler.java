package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.estado.EstadoInputDto;
import com.nesrux.jmfood.api.model.dto.output.estado.EstadoOutputDto;
import com.nesrux.jmfood.domain.model.endereco.Estado;

@Component
public class EstadoInputDisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoOutputDto outputDto) {
		return modelMapper.map(outputDto, Estado.class);
	}

	public void copyTodomainObject(EstadoInputDto outputDto, Estado estado) {
		modelMapper.map(outputDto, estado);
	}
}
