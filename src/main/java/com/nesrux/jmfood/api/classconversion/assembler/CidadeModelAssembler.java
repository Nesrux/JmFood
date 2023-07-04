package com.nesrux.jmfood.api.classconversion.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.CidadeController;
import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;
import com.nesrux.jmfood.api.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.endereco.Cidade;

@Component
public class CidadeModelAssembler extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JmFoodLinks jmFoodLinks;

	public CidadeModelAssembler() {
		super(CidadeController.class, CidadeModel.class);
	}

	@Override
	public CidadeModel toModel(Cidade cidade) {

		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);

		modelMapper.map(cidade, cidadeModel);

		cidadeModel.getEstado().add(jmFoodLinks.linkToEstado(cidadeModel.getEstado().getId()));
		return cidadeModel;
	}

	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToCidade());
	}
}
