package com.nesrux.jmfood.api.classconversion.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.CidadeController;
import com.nesrux.jmfood.api.controller.FormaPagamentoController;
import com.nesrux.jmfood.api.controller.PedidoController;
import com.nesrux.jmfood.api.controller.RestauranteController;
import com.nesrux.jmfood.api.controller.UsuarioController;
import com.nesrux.jmfood.api.controller.subcontrollers.restaurantes.RestauranteProdutoController;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.api.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks jmFoodLinks;
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		mapper.map(pedido, pedidoModel);
	
		pedidoModel.add(jmFoodLinks.linkToPedidos());
	
		// código para o cliente
		pedidoModel.getCliente()
				.add(linkTo(methodOn(UsuarioController.class).buscar(pedido.getCliente().getId())).withSelfRel());

		// código para forma pagamento
		pedidoModel.getFormaPagamento().add(
				linkTo(methodOn(FormaPagamentoController.class).buscar(pedidoModel.getFormaPagamento().getId(), null))
						.withSelfRel());

		// para cidade
		pedidoModel.getEndereco().getCidade()
				.add(linkTo(methodOn(CidadeController.class).buscar(pedidoModel.getEndereco().getCidade().getId()))
						.withSelfRel());

		// Para Restaurante
		pedidoModel.getRestaurante().add(
				linkTo(methodOn(RestauranteController.class).buscar(pedidoModel.getRestaurante().getRestauranteId()))
						.withSelfRel());

		// Para a listagem de pedidos <PedidoResumoModel>
		// pedidoModel.add(linkTo(PedidoController.class).withSelfRel());

		// Para um unico produto
		pedidoModel.getItens()
				.forEach(itemPedido -> itemPedido.getProduto()
						.add(linkTo(methodOn(RestauranteProdutoController.class)
								.buscar(pedidoModel.getRestaurante().getRestauranteId(), itemPedido.getId()))
								.withSelfRel()));

		return pedidoModel;

	}

	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		return super.toCollectionModel(entities).add(linkTo(PedidoController.class).withSelfRel());
	}

}
