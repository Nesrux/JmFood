package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PedidoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.repository.PedidoRepository;

@Component
public class CadastroPedidoService {
	@Autowired
	private PedidoRepository repository;

	public List<Pedido> Listar() {
		return repository.findAll();
	}
	
	public Pedido acharOuFalhar(Long pedidoId) {
		return repository.findById(pedidoId)
				.orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}

}
