package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.nesrux.jmfood.api.classconversion.assembler.CozinhaModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.CozinhaInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.cozinha.CozinhaInputDto;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.openapi.controller.cozinha.CozinhaControllerOpenApi;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping(path ="/cozinhas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController implements CozinhaControllerOpenApi {

	@Autowired
	private CadastroCozinhaService cozinhaService;
	@Autowired
	private CozinhaInputDisassembler inputDisassembler;
	@Autowired
	private CozinhaModelAssembler outputAssembler;

	@GetMapping()
	// A anotação @PageableDefault muda o tamanho dos elementos de uma pagina, o
	// padrão é 20
	public Page<CozinhaModel> listar(@PageableDefault(size = 10) Pageable page) {
		Page<Cozinha> cozinhasPage = cozinhaService.acharTodas(page);

		List<CozinhaModel> cozinhasModel = outputAssembler.toCollectionDto(cozinhasPage.getContent());

		Page<CozinhaModel> cozinhasPageModel = new PageImpl<>(cozinhasModel, page, cozinhasPage.getTotalElements());

		return cozinhasPageModel;
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaModel buscar(@PathVariable Long cozinhaId) {
		return outputAssembler.toModel(cozinhaService.buscaOuFalha(cozinhaId));
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInputDto cozinhaInputDto) {
		Cozinha cozinha = inputDisassembler.toDomainnObject(cozinhaInputDto);
		cozinhaService.salvar(cozinha);

		CozinhaModel cozinhaOutputDto = outputAssembler.toModel(cozinha);

		return cozinhaOutputDto;
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaModel atualizar(@PathVariable Long cozinhaId, @RequestBody @Valid CozinhaInputDto cozinhaInputDto) {
		Cozinha cozinhaAtual = cozinhaService.buscaOuFalha(cozinhaId);

		// Importante
		inputDisassembler.copyToDomainObject(cozinhaInputDto, cozinhaAtual);

		Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaAtual);

		CozinhaModel outputDto = outputAssembler.toModel(cozinhaSalva);

		return outputDto;
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long cozinhaId) {

		cozinhaService.excluir(cozinhaId);

	}
}
