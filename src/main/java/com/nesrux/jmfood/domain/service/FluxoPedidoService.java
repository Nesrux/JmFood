package com.nesrux.jmfood.domain.service;

import java.time.OffsetDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.model.pedido.StatusPedido;

@Service
public class FluxoPedidoService {
	@Autowired
	private CadastroPedidoService pedidoService;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CRIADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedidoId, pedido.getStatus(), StatusPedido.CONFIRMADO));
		}
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

}
