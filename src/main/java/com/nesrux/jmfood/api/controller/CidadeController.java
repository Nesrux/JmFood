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

import com.nesrux.jmfood.api.classconversion.assembler.endereco.CidadeModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.CidadeInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.cidade.CidadeInputDto;
import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;
import com.nesrux.jmfood.api.openapi.controller.cidades.CidadeControllerOpenApi;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.EstadoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeController implements CidadeControllerOpenApi {

	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CidadeInputDisassembler cidadeDisassembler;
	@Autowired
	private CidadeModelAssembler cidadeAssembler;

	@Override
	@GetMapping
	public CollectionModel<CidadeModel> listar() {
		List<Cidade> cidadeList = cidadeService.acharTodas();
		
		return cidadeAssembler.toCollectionModel(cidadeList);
	}

	@Override
	@GetMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModel buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.acharOuFalhar(cidadeId);

		CidadeModel cidadeModel = cidadeAssembler.toModel(cidade);

		return cidadeModel;
	}

	@Override
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(@RequestBody @Valid CidadeInputDto cidadeInputDto) {
		try {
			Cidade cidade = cidadeDisassembler.toDomainObject(cidadeInputDto);
			cidadeService.salvar(cidade);

			return cidadeAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputDto cidadeInputDto) {
		try {
			Cidade cidadeAtual = cidadeService.acharOuFalhar(cidadeId);

			cidadeDisassembler.copyToDomainObject(cidadeInputDto, cidadeAtual);
			cidadeService.salvar(cidadeAtual);

			return cidadeAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
}
