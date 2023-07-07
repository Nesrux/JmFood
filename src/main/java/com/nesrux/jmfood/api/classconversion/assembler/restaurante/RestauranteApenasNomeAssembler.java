package com.nesrux.jmfood.api.classconversion.assembler.restaurante;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.RestauranteController;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteApenasNomeModel;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
@Component
public class RestauranteApenasNomeAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteApenasNomeModel> {
	@Autowired
	private ModelMapper mapper;
	
	public RestauranteApenasNomeAssembler() {
		super(RestauranteController.class, RestauranteApenasNomeModel.class);
	
	}

	@Override
	public RestauranteApenasNomeModel toModel(Restaurante restaurnte) {
		RestauranteApenasNomeModel restauranteModel = createModelWithId(restaurnte.getId(), restaurnte);
		mapper.map(restaurnte, restauranteModel);
		
		return restauranteModel;
	}
	@Override
	public CollectionModel<RestauranteApenasNomeModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(linkTo(RestauranteController.class).withSelfRel());
	}

}
