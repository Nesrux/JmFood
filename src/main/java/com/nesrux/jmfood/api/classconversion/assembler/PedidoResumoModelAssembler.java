package com.nesrux.jmfood.api.classconversion.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoResumoModelAssembler {
	@Autowired
	private ModelMapper mapper;

	public PedidoResumoModel toModel(Pedido pedido) {
		return mapper.map(pedido, PedidoResumoModel.class);
	}

	public List<PedidoResumoModel> toCollectionDto(List<Pedido> pedidos) {
		return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
	}

}
