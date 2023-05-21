package com.nesrux.jmfood.api.classconversion.dissasembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.api.model.dto.input.pedido.PedidoInputDto;
import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Component
public class PedidoInputDisasselber {
	@Autowired
	private ModelMapper mapper;

	public Pedido toDomainObject(PedidoInputDto pedidoDto) {
		return mapper.map(pedidoDto, Pedido.class);
	}

	public void copyToDomainObject(PedidoInputDto pedidoDto, Pedido pedido) {
		mapper.map(pedidoDto, pedido);

	}

}
