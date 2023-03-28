package com.nesrux.jmfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.Cozinha;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}

}
