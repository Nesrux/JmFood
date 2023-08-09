package com.nesrux.jmfood.api.v1.classconversion.assembler.produto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes.RestauranteProdutoController;
import com.nesrux.jmfood.api.v1.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.JmfoodSecurity;
import com.nesrux.jmfood.domain.model.pedido.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private JmFoodLinks links;

	@Autowired
	private JmfoodSecurity jmfoodSecurity;

	public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
		mapper.map(produto, produtoModel);
		if (jmfoodSecurity.podePesquisarPedidos()) {
			produtoModel.add(links.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
			produtoModel.add(links.linkToFotoProduto(produto.getRestaurante().getId(), produtoModel.getId(), "foto"));
		}
		return produtoModel;
	}

	@Override
	public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
		return super.toCollectionModel(entities);
	}
}
