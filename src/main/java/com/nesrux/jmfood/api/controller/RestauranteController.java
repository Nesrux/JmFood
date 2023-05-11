package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.RestauranteOutputAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.RestauranteInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.RestauranteInputDTO;
import com.nesrux.jmfood.api.model.dto.output.RestauranteOutputDTO;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	// private SmartValidator smartValidator; // Faz micro validacoes no bean
	// validation

	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private RestauranteOutputAssembler restauranteAssembler;
	@Autowired
	private RestauranteInputDisassembler restauranteDissasembler;

	@GetMapping()
	public List<RestauranteOutputDTO> listar() {
		return restauranteAssembler.toCollectionDto(restauranteService.acharTodos());
	}

	@GetMapping("/{restauranteId}")
	public RestauranteOutputDTO buscar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		return restauranteAssembler.toModel(restaurante);
	}
	// O @valid vai validar o objeto enviado na requisição na hora que ele chega no
	// metodo adicionar, ao invés dele fazer isso na hora da persistencia de dados,
	// ou seja, ele nem chega a ir para a camada de dominio, facilitando a
	// manipulaçao das exceptions

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteOutputDTO adicionar(@RequestBody @Valid RestauranteInputDTO restauranteInputDTO) {
		try {
			Restaurante restaurante =restauranteDissasembler.toDomainObject(restauranteInputDTO);

			return restauranteAssembler.toModel(restauranteService.salvar(restaurante));

		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{restauranteId}")
	public RestauranteOutputDTO atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInputDTO restauranteInputDto) {
		try {
			Restaurante restauranteAtual = restauranteService.acharOuFalhar(restauranteId);

			BeanUtils.copyProperties(restauranteInputDto, restauranteAtual, "id", "formasPagamento", "endereco",
					"dataCadastro");

			return restauranteAssembler.toModel(restauranteService.salvar(restauranteAtual));
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}



}
