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

		if (!pedido.getStatus().equals(StatusPedido.CRIADO) && !pedido.getStatus().equals(StatusPedido.ENTREGE)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()));
		}
		pedido.setStatus(StatusPedido.CONFIRMADO);
		pedido.setDataConfirmacao(OffsetDateTime.now());
	}

	public void entregar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);

		if (!pedido.getStatus().equals(StatusPedido.CONFIRMADO) && !pedido.getStatus().equals(StatusPedido.ENTREGE)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedidoId, pedido.getStatus().getDescricao(), StatusPedido.ENTREGE.getDescricao()));
		}

		pedido.setStatus(StatusPedido.ENTREGE);
		pedido.setDataEntrega(OffsetDateTime.now());

	}

	@Transactional
	public void cancelar(Long pedidoId) {
		Pedido pedido = pedidoService.acharOuFalhar(pedidoId);
		// Se o pedido nao for um pedido criado ou se o pedido não for um pedido
		// cancelado
		if (!pedido.getStatus().equals(StatusPedido.CRIADO) && !pedido.getStatus().equals(StatusPedido.CANCELADO)) {
			throw new NegocioException(String.format("Status do pedido %d não pode ser alterado de %s para %s",
					pedidoId, pedido.getStatus().getDescricao(), StatusPedido.CANCELADO.getDescricao()));
		}
		pedido.setStatus(StatusPedido.CANCELADO);
		pedido.setDataCancelamento(OffsetDateTime.now());
	}

}
