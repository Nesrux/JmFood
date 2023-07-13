package com.nesrux.jmfood.api.v2.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v2.controller.CozinhaControllerV2;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;
import com.nesrux.jmfood.api.v2.utils.JmFoodLinksV2;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;

@Component
public class CozinhaModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JmFoodLinksV2 jmFoodLinks;

	public CozinhaModelAssemblerV2() {
		super(CozinhaControllerV2.class, CozinhaModelV2.class);
	}

	public CozinhaModelV2 toModel(Cozinha cozinha) {
		CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);

		cozinhaModel.add(jmFoodLinks.linkToCozinhas("cozinhas"));

		return cozinhaModel;
	}

	@Override
	public CollectionModel<CozinhaModelV2> toCollectionModel(Iterable<? extends Cozinha> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToCozinhas());
	}
}
