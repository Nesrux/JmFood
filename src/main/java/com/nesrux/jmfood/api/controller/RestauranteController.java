package com.nesrux.jmfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.Restaurante;
import com.nesrux.jmfood.domain.repository.RestauranteRepository;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	@Autowired
	private RestauranteRepository repository;

	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping()
	public List<Restaurante> listar() {
		return repository.findAll();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
		Optional<Restaurante> restaurante = repository.findById(restauranteId);

		if (restaurante.isPresent()) {
			return ResponseEntity.ok(restaurante.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.savar(restaurante);

			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Optional<Restaurante> restauranteAtual = repository.findById(restauranteId);
			if (restauranteAtual != null) {
				BeanUtils.copyProperties(restaurante, restauranteAtual, "id");

				Restaurante restauranteSalvo = restauranteService.savar(restauranteAtual.get());
				return ResponseEntity.ok().body(restauranteSalvo);
			}
			return ResponseEntity.notFound().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("{restauranteId}")
	public ResponseEntity<?> atualizaParcial(@PathVariable Long restauranteId,
			@RequestBody Map<String, Object> campos) {
		Optional<Restaurante> restauranteAtual = repository.findById(restauranteId);

		if (restauranteAtual == null) {
			return ResponseEntity.notFound().build();
		}
		merge(campos, restauranteAtual.get());

		return atualizar(restauranteId, restauranteAtual.get());
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restaurantesDestino) {
		ObjectMapper mapper = new ObjectMapper();

		Restaurante restauranteOrigem = mapper.convertValue(camposOrigem, Restaurante.class);

		camposOrigem.forEach((propriedade, valor) -> {
		
			Field field = ReflectionUtils.findField(Restaurante.class, propriedade);
			field.setAccessible(true);

			@SuppressWarnings("unused")
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

			ReflectionUtils.setField(field, restaurantesDestino, valor);
		});
	}
}
