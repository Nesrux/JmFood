package com.nesrux.jmfood.api.v1.classconversion.assembler.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteFotoProdutoController;
import com.nesrux.jmfood.api.v1.model.dto.output.fotoProduto.FotoProdutoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.pedido.FotoProduto;

@Component
public class FotoProdutoModelAssembler extends RepresentationModelAssemblerSupport<FotoProduto, FotoProdutoModel> {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks links;

	public FotoProdutoModelAssembler() {
		super(RestauranteFotoProdutoController.class, FotoProdutoModel.class);
	}

	public FotoProdutoModel toModel(FotoProduto fotoProduto) {
		FotoProdutoModel fotoModel = new FotoProdutoModel();
		mapper.map(fotoProduto, fotoModel);
		
		fotoModel.add(links.linkToFotoProduto(fotoProduto.getRestauranteId(), fotoProduto.getId()));;

		fotoModel.add(links.linkToProduto(fotoProduto.getRestauranteId(), fotoProduto.getProduto().getId(), "produtos"));
		return fotoModel;
	}

}
