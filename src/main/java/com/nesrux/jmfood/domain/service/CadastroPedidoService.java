package com.nesrux.jmfood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.PedidoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.pedido.Pedido;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.PedidoRepository;

@Component
public class CadastroPedidoService {
	@Autowired
	private PedidoRepository repository;

	public List<Pedido> Listar() {
		return repository.findAll();
	}

	public Pedido acharOuFalhar(Long pedidoId) {
		return repository.findById(pedidoId).orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
	}

	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		pedido.setCliente(usuario);
		/*
		 * Por padrão todos os pedidos vão estar atribuidos à o usuario de ID 1, pois
		 * eventualmente vou adicioar a autenticação para fazer a validação de usuarios
		 * e a criação de pedidos utilizando o spring securyt
		 */

		return repository.save(pedido);
	}

}
