package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoOutputDto;
import com.nesrux.jmfood.domain.model.endereco.Estado;

public class EstadoOutputAssembler {

	@Autowired
	private ModelMapper mapper;

	public EstadoOutputDto toModel(Estado estado) {
		return mapper.map(estado, EstadoOutputDto.class);
	}

	public List<EstadoOutputDto> toCollectionDto(List<Estado> estados) {
		return estados.stream().map(estado -> mapper.map(estados, EstadoOutputDto.class)).toList();
	}
}
