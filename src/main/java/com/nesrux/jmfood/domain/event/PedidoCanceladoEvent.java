package com.nesrux.jmfood.domain.event;

import com.nesrux.jmfood.domain.model.pedido.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

	private Pedido pedido;
}
