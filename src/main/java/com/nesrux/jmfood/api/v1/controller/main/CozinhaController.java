package com.nesrux.jmfood.api.v1.controller.main;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.classconversion.assembler.cozinha.CozinhaModelAssembler;
import com.nesrux.jmfood.api.v1.classconversion.dissasembler.CozinhaInputDisassembler;
import com.nesrux.jmfood.api.v1.model.dto.input.cozinha.CozinhaInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.v1.openapi.controller.cozinha.CozinhaControllerOpenApi;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(path = "/v1/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CadastroCozinhaService cozinhaService;
	@Autowired
	private CozinhaInputDisassembler disassembler;
	@Autowired
	private CozinhaModelAssembler assembler;
	@Autowired
	private PagedResourcesAssembler<Cozinha> pagedModelAssembler;

	@PreAuthorize("isAuthenticated()")
	@GetMapping()
	@Override
	public PagedModel<CozinhaModel> listar(@PageableDefault(size = 10) Pageable page) {
		Page<Cozinha> cozinhasPage = cozinhaService.acharTodas(page);

		PagedModel<CozinhaModel> cozinhaPagedModel = pagedModelAssembler.toModel(cozinhasPage, assembler);
		return cozinhaPagedModel;
	}

	@PreAuthorize("isAuthenticated()")
	@Override
	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return assembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
	}
	

	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@Override
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInputDto cozinhaInputDto) {
		Cozinha cozinha = disassembler.toDomainnObject(cozinhaInputDto);
		cozinhaService.salvar(cozinha);

		CozinhaModel cozinhaOutputDto = assembler.toModel(cozinha);

		return cozinhaOutputDto;
	}
	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@Override
	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDto cozinhaInputDto) {
		Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);

		// Importante
		disassembler.copyToDomainObject(cozinhaInputDto, cozinhaAtual);

		Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaAtual);

		CozinhaModel outputDto = assembler.toModel(cozinhaSalva);

		return outputDto;
	}
	@PreAuthorize("hasAuthority('EDITAR_COZINHAS')")
	@Override
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long cozinhaId) {

		cozinhaService.excluir(cozinhaId);

	}
}
