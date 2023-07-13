package com.nesrux.jmfood.api.v2.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.CozinhaController;
import com.nesrux.jmfood.api.v1.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaModelAssembler extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JmFoodLinks jmFoodLinks;

	public CozinhaModelAssembler() {
		super(CozinhaController.class, CozinhaModel.class);
	}

	public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(jmFoodLinks.linkToCozinhas("cozinhas"));

		return cozinhaModel;
	}

	@Override
	public CollectionModel<CozinhaModel> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToCozinhas());
	}
}
