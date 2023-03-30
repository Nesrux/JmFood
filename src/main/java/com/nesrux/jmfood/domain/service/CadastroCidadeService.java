package com.nesrux.jmfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.Cidade;
import com.nesrux.jmfood.domain.model.Estado;
import com.nesrux.jmfood.domain.repository.CidadeRepository;
import com.nesrux.jmfood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {
	@Autowired
	private CidadeRepository repository;

	@Autowired
	private EstadoRepository estadoRepository;

	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);

		if (estado == null) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe cadastro de estado com código %d", estadoId));
		}

		cidade.setEstado(estado);

		return repository.salvar(cidade);

	}
}