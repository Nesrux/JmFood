package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.FormaPagamentoNaoEncontradaException;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.repository.FormaPagamentoRepository;

@Component
public class CadastroFormaPagamentoService {
	@Autowired
	private FormaPagamentoRepository repository;

	public FormaPagamento acharOuFalhar(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}
	
	public List<FormaPagamento> acharTodos(){
		return repository.findAll();
	}	

	
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		
		
	}
	public void excluir () {}
	
}
