package com.nesrux.jmfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.repository.CozinhaRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	@Autowired
	private CozinhaRepository repository;

//	@GetMapping("/cozinhas/por-nome")
//	public List<Cozinha> cozinhaPorNome(@RequestParam String nome) {
//		return repository.buscarNome(nome);
//	}
}
