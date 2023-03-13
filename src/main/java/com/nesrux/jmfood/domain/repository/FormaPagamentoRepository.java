package com.nesrux.jmfood.domain.repository;

import java.util.List;

import com.nesrux.jmfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	FormaPagamento salvar(FormaPagamento pagamento);

	FormaPagamento buscar(Long id);

	void remover(FormaPagamento pagamento);

	List<FormaPagamento> listar();

}
