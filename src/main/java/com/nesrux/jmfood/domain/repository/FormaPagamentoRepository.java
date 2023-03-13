package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {
	List<FormaPagamento> listar();

	FormaPagamento salvar(FormaPagamento pagamento);

	void remover(FormaPagamento pagamento);

	FormaPagamento buscar(Long id);
}
