package com.nesrux.jmfood.api.v1.classconversion.assembler.pedido;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.v1.controller.main.PedidoController;
import com.nesrux.jmfood.api.v1.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.JmfoodSecurity;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private JmFoodLinks jmFoodLinks;
	@Autowired
	private JmfoodSecurity jmfoodSecurity;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		mapper.map(pedido, pedidoResumoModel);
		if (jmfoodSecurity.podePesquisarPedidos()) {
			pedidoResumoModel.add(jmFoodLinks.linkToPedidos("pedidos"));

			pedidoResumoModel.getCliente().add(jmFoodLinks.linkToUsuario(pedidoResumoModel.getCliente().getId()));

			pedidoResumoModel.getRestaurante()
					.add(jmFoodLinks.linkToRestaurante(pedidoResumoModel.getRestaurante().getRestauranteId()));
		}
		return pedidoResumoModel;
	}

}
