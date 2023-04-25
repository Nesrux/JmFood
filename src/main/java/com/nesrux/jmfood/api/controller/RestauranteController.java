package com.nesrux.jmfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping()
	public List<Restaurante> listar() {
		return restauranteService.acharTodos();
	}

	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		return restaurante;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		try {
			return restaurante = restauranteService.salvar(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PutMapping("/{restauranteId}")
	public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante) {
		try {
			Restaurante restauranteAtual = restauranteService.acharOuFalhar(restauranteId);

			BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
					"dataCadastro");

			return restauranteService.salvar(restauranteAtual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

	@PatchMapping("{restauranteId}")
	public Restaurante atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
		Restaurante restauranteAtual = restauranteService.acharOuFalhar(restauranteId);

		merge(campos, restauranteAtual);

		return atualizar(restauranteId, restauranteAtual);
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
