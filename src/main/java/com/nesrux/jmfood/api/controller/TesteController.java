package com.nesrux.jmfood.api.controller;

import static com.nesrux.jmfood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.nesrux.jmfood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

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
		return restauranteRepository.acharPorNomeEId(nome, id);
	}

	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> pRestaurante(String nome) {
		return restauranteRepository.findFristRestauranteByNomeContaining(nome);

	}

	@GetMapping("/restaurantes/top2")
	public List<Restaurante> top2Restaurante(String nome) {
		return restauranteRepository.findTop2RestauranteByNomeContaining(nome);

	}

	@GetMapping("/restaurantes/quantidade")
	public int quantidadeDeCozinhas(Long id) {
		return restauranteRepository.countByCozinhaId(id);
	}

	@GetMapping("restaurantes/por-nome-taxa")
	public List<Restaurante> seila(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.find(nome, taxaInicial, taxaFinal);
	}
	//TODO Estudar sobre DDD e as implementações do Spring, como a especfication
	@GetMapping("restaurantes/com-frete-gratis")
	public List<Restaurante> comFreteGratuito(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}
}
