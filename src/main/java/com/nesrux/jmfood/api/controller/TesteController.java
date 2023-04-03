package com.nesrux.jmfood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.model.Cozinha;
import com.nesrux.jmfood.domain.model.Restaurante;
import com.nesrux.jmfood.domain.repository.CozinhaRepository;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {
	@Autowired
	private CozinhaRepository repository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhaPorNome(@RequestParam String nome) { // escrever sobre o request param ele ja vem como
																		// padrao sem precisar implementar nada!
		return repository.findByNomeContaining(nome);
	}

	@GetMapping("/cozinhas/existe")
	public boolean existeCozinha(String nome) {
		return repository.existsByNome(nome);
	}

	@GetMapping("/restaurantes/por-taxa")
	public List<Restaurante> RestaurantePorTaxaFrete(BigDecimal v1, BigDecimal v2) {
		return restauranteRepository.findByTaxaFreteBetween(v1, v2);
	}

	@GetMapping("/restaurantes/por-nome-e-id")
	public List<Restaurante> restaurantePorNomeEid(String nome, Long id) {
		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
	}

	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> pRestaurante(String nome) {
		return restauranteRepository.findFristRestauranteByNomeContaining(nome);

	}

	@GetMapping("/restaurantes/top2")
	public List<Restaurante> top2Restaurante(String nome) {
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);

	}
}
