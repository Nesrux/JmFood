package com.nesrux.jmfood.api.v1.classconversion.assembler.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.main.RestauranteController;
import com.nesrux.jmfood.api.v1.model.dto.output.restaurante.RestauranteBasicoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteBasicoAssembler
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks links;

	public RestauranteBasicoAssembler() {
		super(RestauranteController.class, RestauranteBasicoModel.class);
	}

	@Override
	public RestauranteBasicoModel toModel(Restaurante restaurante) {
		RestauranteBasicoModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, restauranteModel);

		restauranteModel.add(links.linkToRestaurantes("restaurantes"));
		restauranteModel.getCozinha().add(links.linkToCozinha(restauranteModel.getId()));

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(links.linkToRestaurantes());
	}
}
