package com.nesrux.jmfood.api.v2.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.api.v2.controller.CidadeControllerV2;
import com.nesrux.jmfood.api.v2.model.output.cidade.CidadeModelV2;
import com.nesrux.jmfood.domain.model.endereco.Cidade;

@Component
public class CidadeModelAssemblerV2 extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JmFoodLinks jmFoodLinks;

	public CidadeModelAssemblerV2() {
		super(CidadeControllerV2.class, CidadeModelV2.class);
	}

	@Override
	public CidadeModelV2 toModel(Cidade cidade) {
		CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeModel);

		cidadeModel.add(jmFoodLinks.linkToCidades("cidades"));

		cidadeModel.getEstado().add(jmFoodLinks.linkToEstado(cidadeModel.getEstado().getId()));
		return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToCidades());
	}
}
