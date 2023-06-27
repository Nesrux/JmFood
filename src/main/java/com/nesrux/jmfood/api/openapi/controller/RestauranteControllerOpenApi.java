package com.nesrux.jmfood.api.openapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;
import com.nesrux.jmfood.api.model.dto.input.restaurante.RestauranteInputDto;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteModel;
import com.nesrux.jmfood.api.model.dto.view.RestauranteView;
import com.nesrux.jmfood.api.openapi.model.RestauranteBasicoOpenApi;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

public interface RestauranteControllerOpenApi {

	@ApiOperation(value ="Listagem de restaurantes", response = RestauranteBasicoOpenApi.class)
	@ApiImplicitParams({
		@ApiImplicitParam(value = "nome da projeção de pedidos", allowableValues = "apenas-nome",
				name = "projeção", paramType = "query", type = "string")
	})
	@JsonView(RestauranteView.resumo.class)
	@GetMapping
	public List<RestauranteModel> listarResumo();

	@ApiOperation(value ="Listagem de restaurantes", hidden =  true)
	@JsonView(RestauranteView.apenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteModel> listarNomes();

	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Long restauranteId);
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInputDto restauranteInputDTO);

	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Long restauranteId,
			@RequestBody @Valid RestauranteInputDto restauranteInputDto);

	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarRestaurantes(@RequestBody List<Long> restaurantes);

	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desativarRestaurantes(@RequestBody List<Long> restauranteids);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/ativo")
	public void ativarRestaurante(@PathVariable Long restauranteId);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{restauranteId}/ativo")
	public void desativarRestaurante(@PathVariable Long restauranteId);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/fechamento")
	public void fecharRestaurante(@PathVariable Long restauranteId);

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{restauranteId}/abertura")
	public void abrirRestaurante(@PathVariable Long restauranteId);


}
