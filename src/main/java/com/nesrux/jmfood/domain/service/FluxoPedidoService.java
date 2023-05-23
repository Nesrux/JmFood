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
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);
		pedido.confirmarPedido();

	}

	@Transactional
	public void entregar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);
		pedido.entregarPedido();

	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);
		pedido.cancelarPedido();
	}

}
