package com.nesrux.jmfood.api.classconversion.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.CozinhaController;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	@Autowired
	private ModelMapper modelMapper;

	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(linkTo(CozinhaController.class).withRel("cozinhas"));

		return cozinhaModel;
	}

	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities).add(linkTo(CozinhaController.class).withSelfRel());
	}
}
