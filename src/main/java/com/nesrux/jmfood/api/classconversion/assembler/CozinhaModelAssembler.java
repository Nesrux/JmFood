package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.CozinhaController;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	public CozinhaModelAssembler() {
		super(Cozinha.class, CozinhaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaModel toModel(Cozinha cozinha) {
		return modelMapper.map(CozinhaController.class, CozinhaModel.class);
	}

	public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
		return cozinhas.stream().map(cozinha -> toModel(cozinha)).collect(Collectors.toList());
	}

}
