package com.nesrux.jmfood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.endereco.Estado;
import com.nesrux.jmfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com o código %d";

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;

	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void excluir(Long estadoId) {
		try {
			Optional<Estado> estado = estadoRepository.findById(estadoId);
			estadoRepository.delete(estado.get());

		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format(MSG_COZINHA_NAO_ENCONTRADA, estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_COZINHA_EM_USO, estadoId));
		}
	}
	
	public Estado achaOuFaha(Long estadoId) {
		return estadoRepository.findById(estadoId)
				.orElseThrow(() -> 
				new EntidadeNaoEncontradaException(String.format(MSG_COZINHA_EM_USO, estadoId)));
	}
}
