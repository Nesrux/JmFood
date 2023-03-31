package com.nesrux.jmfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.nesrux.jmfood.domain.model.FormaPagamento;
import com.nesrux.jmfood.domain.repository.FormaPagamentoRepository;
@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public FormaPagamento buscar(Long id) {
		return manager.find(FormaPagamento.class, id);
	}

	@Override
	public FormaPagamento salvar(FormaPagamento pagamento) {
		return manager.merge(pagamento);
	}

	@Override
	public void remover(FormaPagamento pagamento) {
		pagamento = buscar(pagamento.getId());
		manager.remove(pagamento);
	}

	@Override
	public List<FormaPagamento> listar() {
		return manager.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}
}
