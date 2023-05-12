package com.nesrux.jmfood.api.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoOutputDto;

public class EstadoOutputAssembler {
	
	@Autowired
	private ModelMapper mapper;
	
	public EstadoOutputDto toModel() {
		return null;
	}

}
