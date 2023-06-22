package com.nesrux.jmfood.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.FormaPagamentoNaoEncontradaException;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.repository.FormaPagamentoRepository;

@Service
public class CadastroFormaPagamentoService {

	private static final String MSG_FORMAPAGAMENTO_EM_USO = "Forma de pagamento de código %d não pode ser removida, pois está em uso";
	@Autowired
	private FormaPagamentoRepository repository;

	public FormaPagamento acharOuFalhar(Long formaPagamentoId) {
		return repository.findById(formaPagamentoId)
				.orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoId));
	}

	public List<FormaPagamento> acharTodos() {
		return repository.findAll();
	}
	//ultima atualização geral, para listagem
	public OffsetDateTime ultimaAtualizacao() {
		return repository.getDataUltimaAtualizacao();
	}
	
	public OffsetDateTime ultimaAtualizacao(Long formaPagamentoId) {
		return repository.getDataAtualizacaoById(formaPagamentoId);
	}

	@Transactional
	public FormaPagamento salvar(FormaPagamento formaPagamento) {
		return repository.save(formaPagamento);
	}

	@Transactional
	public void excluir(Long formaPagamentoId) {
		try {
			repository.deleteById(formaPagamentoId);
			repository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new FormaPagamentoNaoEncontradaException(formaPagamentoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_FORMAPAGAMENTO_EM_USO, formaPagamentoId));
		}
	}

}
