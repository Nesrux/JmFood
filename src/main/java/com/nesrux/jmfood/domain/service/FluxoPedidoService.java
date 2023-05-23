package com.nesrux.jmfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.pedido.Pedido;

@Service
public class FluxoPedidoService {
	@Autowired
	private CadastroPedidoService pedidoService;

	@Transactional
	public void confirmar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.confirmarPedido();

	}

	@Transactional
	public void entregar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.entregarPedido();

	}

	@Transactional
	public void cancelar(String codigoPedido) {
		Pedido pedido = pedidoService.acharOuFalhar(codigoPedido);
		pedido.cancelarPedido();
	}

}
