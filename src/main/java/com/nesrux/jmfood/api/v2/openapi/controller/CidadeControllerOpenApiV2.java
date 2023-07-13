package com.nesrux.jmfood.api.v2.openapi.controller;

import org.springframework.hateoas.CollectionModel;

import com.nesrux.jmfood.api.v2.model.input.cidade.CidadeInputDtoV2;
import com.nesrux.jmfood.api.v2.model.output.cidade.CidadeModelV2;

public interface CidadeControllerOpenApiV2 {

	CollectionModel<CidadeModelV2> listar();

	CidadeModelV2 buscar(Long cidadeId);

	CidadeModelV2 adicionar(CidadeInputDtoV2 cidadeInputDto);

	CidadeModelV2 atualizar(Long cidadeId, CidadeInputDtoV2 cidadeInputDto);

	void excluir(Long cidadeId);

}