package com.nesrux.jmfood.api.v2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
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

import com.nesrux.jmfood.api.v2.classconversion.assembler.CozinhaModelAssemblerV2;
import com.nesrux.jmfood.api.v2.classconversion.dissassembler.CozinhaInputDisassemblerV2;
import com.nesrux.jmfood.api.v2.model.input.cozinha.CozinhaInputDtoV2;
import com.nesrux.jmfood.api.v2.model.output.cozinha.CozinhaModelV2;
import com.nesrux.jmfood.api.v2.openapi.controller.CozinhaControllerOpenApiV2;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(path = "/v2/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaControllerV2 implements CozinhaControllerOpenApiV2 {

	@Autowired
	private CadastroCozinhaService cozinhaService;
	@Autowired
	private CozinhaInputDisassemblerV2 disassembler;
	@Autowired
	private CozinhaModelAssemblerV2 assembler;
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedModelAssembler;

	@Override
	@GetMapping()
	@Deprecated
	public PagedModel<CozinhaModelV2> listar(@PageableDefault(size = 10) Pageable page) {
		Page<Cozinha> cozinhasPage = cozinhaService.acharTodas(page);

		PagedModel<CozinhaModelV2> cozinhaPagedModel = pagedModelAssembler.toModel(cozinhasPage, assembler);
		return cozinhaPagedModel;
	}

	@Override
	@GetMapping("/{cozinhaId}")
	@Deprecated
	public CozinhaModelV2 buscar(@PathVariable Long cozinhaId) {
		return assembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
	}

	@Override
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	@Deprecated
	public CozinhaModelV2 adicionar(@RequestBody @Valid CozinhaInputDtoV2 cozinhaInputDto) {
		Cozinha cozinha = disassembler.toDomainnObject(cozinhaInputDto);
		cozinhaService.salvar(cozinha);

		CozinhaModelV2 cozinhaOutputDto = assembler.toModel(cozinha);

		return cozinhaOutputDto;
	}

	@Override
	@PutMapping("/{cozinhaId}")
	@Deprecated
	public CozinhaModelV2 atualizar(@PathVariable Long cozinhaId,
			@RequestBody @Valid CozinhaInputDtoV2 cozinhaInputDto) {
		Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);

		// Importante
		disassembler.copyToDomainObject(cozinhaInputDto, cozinhaAtual);

		Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaAtual);

		CozinhaModelV2 outputDto = assembler.toModel(cozinhaSalva);

		return outputDto;
	}

	@Override
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@Deprecated
	public void deletar(@PathVariable Long cozinhaId) {

		cozinhaService.excluir(cozinhaId);

	}
}
