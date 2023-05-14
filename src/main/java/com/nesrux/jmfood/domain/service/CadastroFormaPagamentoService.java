package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.repository.FormaPagamentoRepository;

@Component
public class CadastroFormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository repository;

	public FormaPagamento acharOuFalhar(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(formaPagamentoId));
	}
	
	public List<FormaPagamento> acharTodos(){}	

	
	public FormaPagamento salvar() {}
	public void excluir () {}
	
}
