package com.nesrux.jmfood.api.classconversion.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.controller.PedidoController;
import com.nesrux.jmfood.api.controller.RestauranteController;
import com.nesrux.jmfood.api.controller.UsuarioController;
import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {
	@Autowired
	private ModelMapper mapper;

	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

	@Override
	public PedidoResumoModel toModel(Pedido pedido) {
		PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
		mapper.map(pedido, pedidoResumoModel);

		pedidoResumoModel.add(linkTo(PedidoController.class).withRel("pedidos"));

		pedidoResumoModel.getCliente().add(
				linkTo(methodOn(UsuarioController.class).buscar(pedidoResumoModel.getCliente().getId())).withSelfRel());

		pedidoResumoModel.getRestaurante().add(linkTo(
				methodOn(RestauranteController.class).buscar(pedidoResumoModel.getRestaurante().getRestauranteId()))
				.withSelfRel());

		return pedidoResumoModel;
	}

}
