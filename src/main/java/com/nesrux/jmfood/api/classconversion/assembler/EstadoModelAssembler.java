package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoModel;
import com.nesrux.jmfood.domain.model.endereco.Estado;
@Component
public class EstadoModelAssembler {

	@Autowired
	private ModelMapper mapper;

	public List<EstadoModel> toCollectionDto(List<Estado> estados) {
		return estados.stream().map(estado -> toModel(estado)).collect(Collectors.toList());
	}

	public EstadoModel toModel(Estado estado) {
		return mapper.map(estado, EstadoModel.class);
	}


}
