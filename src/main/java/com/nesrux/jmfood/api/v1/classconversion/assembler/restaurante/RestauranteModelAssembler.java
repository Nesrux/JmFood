package com.nesrux.jmfood.api.v1.classconversion.assembler.restaurante;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.main.RestauranteController;
import com.nesrux.jmfood.api.v1.model.dto.output.restaurante.RestauranteModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.JmfoodSecurity;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks links;
	@Autowired
	private JmfoodSecurity jmfoodSecurity;

	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}

	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		mapper.map(restaurante, restauranteModel);

		restauranteModel.add(links.linkToRestauranteResponsaveis(restauranteModel.getId(), "resposaveis"));

		if (jmfoodSecurity.podeConsultarCozinhas()) {
			restauranteModel.getCozinha().add(links.linkToCozinha(restauranteModel.getCozinha().getId()));
		}
		if (jmfoodSecurity.podeConsultarFormasPagamento()) {
			restauranteModel.add(links.linkToRestauranteFormasPagamento(restaurante.getId(), "forma-pagamento"));
		}
		if (restaurante.verificaEnderecoNulo()) {

			restauranteModel.getEndereco().getCidade()
					.add(links.linkToCidade(restauranteModel.getEndereco().getCidade().getId()));
		}

		restauranteModel.add(links.linkToProdutos(restauranteModel.getId(), "produtos"));

		restauranteModel.add(links.linkToRestaurantes("restaurantes"));

		return restauranteModel;
	}

	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities).add(links.linkToRestaurantes());
	}

}
