package com.nesrux.jmfood.api.v2.openapi.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

import com.nesrux.jmfood.api.v2.model.input.cozinha.CozinhaInputDtoV2;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;

public interface CozinhaControllerOpenApiV2 {

	PagedModel<CozinhaModelV2> listar(Pageable page);

	CozinhaModelV2 buscar(Long cozinhaId);

	CozinhaModelV2 adicionar(CozinhaInputDtoV2 cozinhaInputDto);

	CozinhaModelV2 atualizar(Long cozinhaId, CozinhaInputDtoV2 cozinhaInputDto);

	void deletar(Long cozinhaId);

}