package com.nesrux.jmfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.Cidade;
import com.nesrux.jmfood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	@Autowired
	private CidadeRepository repository;

	public Cidade salvar(Cidade cidade) {
		return repository.salvar(cidade);
	}

}
