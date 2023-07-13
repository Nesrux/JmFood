package com.nesrux.jmfood.api.v1.classconversion.assembler.endereco;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.EstadoController;
import com.nesrux.jmfood.api.v1.model.dto.output.estado.EstadoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.endereco.Estado;

@Component
public class EstadoModelAssembler extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks jmFoodLinks;

	public EstadoModelAssembler() {
		super(EstadoController.class, EstadoModel.class);

	}

	public EstadoModel toModel(Estado estado) {
		EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
		mapper.map(estado, estadoModel);

		// Adiciona link da listagem de estados
		estadoModel.add(jmFoodLinks.linkToEstados("estados"));

		// Adiciona link do recurso do proprio Estado
		// estadoModel.add(linkTo(methodOn(EstadoController.class).buscar(estado.getId())).withSelfRel());

		return estadoModel;
	}

	@Override
	public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToEstados());
	}

}
