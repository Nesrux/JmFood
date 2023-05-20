package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoModel;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoModelAssembler {
	@Autowired
	private ModelMapper mapper;

	public PedidoModel toModel(Pedido pedido) {
		return mapper.map(pedido, PedidoModel.class);
	}

	public List<PedidoModel> toCollectionDto(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}

}
