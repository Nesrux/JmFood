package com.nesrux.jmfood.api.classconversion.assembler.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.PedidoController;
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

		pedidoModel.add(jmFoodLinks.linkToPedidos("pedidos"));

		// código para o cliente
		pedidoModel.getCliente().add(jmFoodLinks.linkToUsuario(pedidoModel.getCliente().getId()));

		// código para forma pagamento
		pedidoModel.getFormaPagamento().add(jmFoodLinks.linkToFormaPagamento(pedidoModel.getFormaPagamento().getId()));

		// para cidade
		pedidoModel.getEndereco().getCidade()
				.add(jmFoodLinks.linkToCidade(pedidoModel.getEndereco().getCidade().getId()));

		// Para Restaurante
		pedidoModel.getRestaurante()
				.add(jmFoodLinks.linkToRestaurante(pedidoModel.getRestaurante().getRestauranteId()));

		// Para um unico produto
		pedidoModel.getItens().forEach(itemPedido -> itemPedido.getProduto()
				.add(jmFoodLinks.linkToProduto(pedidoModel.getRestaurante().getRestauranteId(), itemPedido.getId())));

		// para confimrar pedido
		if (pedido.podeSerConfirmado()) {
			pedidoModel.add(jmFoodLinks.linkToConfirmacaoPedido(pedidoModel.getCodigo(), "confirmar-pedido"));
		}

		// Entregar pedido
		if (pedido.podeSerEntegue()) {
			pedidoModel.add(jmFoodLinks.linkToEntregaPedido(pedidoModel.getCodigo(), "entregar-pedido"));
		}
		// Cancelar Pedido
		if (pedido.podeSerCancelado()) {
			pedidoModel.add(jmFoodLinks.linkToCancelamentoPedido(pedidoModel.getCodigo(), "cancelar-pedido"));

		}
		return pedidoModel;

	}

	@Override
	public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
		return super.toCollectionModel(entities).add(jmFoodLinks.linkToPedidos());
	}

}
