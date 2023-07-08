package com.nesrux.jmfood.api.classconversion.assembler.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.PedidoController;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.api.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks jmFoodLinks;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		mapper.map(pedido, pedidoResumoModel);

		pedidoResumoModel.add(jmFoodLinks.linkToPedidos("pedidos"));

		pedidoResumoModel.getCliente().add(jmFoodLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));

		pedidoResumoModel.getRestaurante()
				.add(jmFoodLinks.linkToRestaurante(pedidoResumoModel.getRestaurante().getRestauranteId()));

		return pedidoResumoModel;
	}

}
