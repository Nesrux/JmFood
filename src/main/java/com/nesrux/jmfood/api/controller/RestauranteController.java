package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.restaurante.RestauranteApenasNomeAssembler;
import com.nesrux.jmfood.api.classconversion.assembler.restaurante.RestauranteBasicoAssembler;
import com.nesrux.jmfood.api.classconversion.assembler.restaurante.RestauranteModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.RestauranteInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.restaurante.RestauranteInputDto;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteApenasNomeModel;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteBasicoModel;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteModel;
import com.nesrux.jmfood.api.openapi.controller.restaurante.RestauranteControllerOpenApi;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.RestauranteNaoEncontradoException;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteController implements RestauranteControllerOpenApi {

	@Autowired
	private CadastroRestauranteService service;
	@Autowired
	private RestauranteModelAssembler restauranteAssembler;
	@Autowired
	private RestauranteInputDisassembler restauranteDissasembler;
	@Autowired
	private RestauranteBasicoAssembler basicoAssembler;
	@Autowired
	private RestauranteApenasNomeAssembler nomeAssembler;

	@Override
	@GetMapping
	public CollectionModel<RestauranteBasicoModel> listarResumo() {
	return basicoAssembler.toCollectionModel(service.acharTodos());
	}

	@Override
	@GetMapping(params = "projecao=apenas-nome")
	public CollectionModel<RestauranteApenasNomeModel> listarNomes() {
		return nomeAssembler.toCollectionModel(service.acharTodos());
	}

	@Override
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = service.acharOuFalhar(restauranteId);

		return restauranteAssembler.toModel(restaurante);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInputDto restauranteInputDTO) {
		try {
			Restaurante restaurante = restauranteDissasembler.toDomainObject(restauranteInputDTO);

			return restauranteAssembler.toModel(service.salvar(restaurante));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInputDto restauranteInputDto) {
		try {
			Restaurante restauranteAtual = service.acharOuFalhar(restauranteId);

			restauranteDissasembler.copyTodomainObject(restauranteInputDto, restauranteAtual);

			return restauranteAssembler.toModel(service.salvar(restauranteAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurantes(@RequestBody List<Long> restauranteids) {
		try {
			service.ativar(restauranteids);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarRestaurantes(@RequestBody List<Long> restauranteids) {
		try {
			service.desativar(restauranteids);
		} catch (RestauranteNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}

	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/ativo")
	public void ativarRestaurante(@PathVariable Long restauranteId) {
		service.ativar(restauranteId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}/ativo")
	public void desativarRestaurante(@PathVariable Long restauranteId) {
		service.desativar(restauranteId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/fechamento")
	public void fecharRestaurante(@PathVariable Long restauranteId) {
		service.fechar(restauranteId);
	}

	@Override
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/abertura")
	public void abrirRestaurante(@PathVariable Long restauranteId) {
		service.abrir(restauranteId);
	}

	// O @valid vai validar o objeto enviado na requisição na hora que ele chega no
	// metodo adicionar, ao invés dele fazer isso na hora da persistencia de dados,
	// ou seja, ele nem chega a ir para a camada de dominio, facilitando a
	// manipulaçao das exceptions

}
