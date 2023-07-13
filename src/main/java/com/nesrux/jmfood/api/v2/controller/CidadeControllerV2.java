package com.nesrux.jmfood.api.v2.controller;

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

import com.nesrux.jmfood.api.v2.classconversion.assembler.CidadeModelAssemblerV2;
import com.nesrux.jmfood.api.v2.classconversion.dissassembler.CidadeInputDisassemblerV2;
import com.nesrux.jmfood.api.v2.model.input.cidade.CidadeInputDtoV2;
import com.nesrux.jmfood.api.v2.model.output.cidade.CidadeModelV2;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.EstadoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path = "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 {

	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CidadeInputDisassemblerV2 cidadeDisassembler;
	@Autowired
	private CidadeModelAssemblerV2 cidadeAssembler;

	@GetMapping
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> cidadeList = cidadeService.acharTodas();

		return cidadeAssembler.toCollectionModel(cidadeList);
	}

	@GetMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.acharOuFalhar(cidadeId);

		CidadeModelV2 cidadeModel = cidadeAssembler.toModel(cidade);

		return cidadeModel;
	}

	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody @Valid CidadeInputDtoV2 cidadeInputDto) {
		try {
			Cidade cidade = cidadeDisassembler.toDomainObject(cidadeInputDto);
			cidadeService.salvar(cidade);

			return cidadeAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeModelV2 atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputDtoV2 cidadeInputDto) {
		try {
			Cidade cidadeAtual = cidadeService.acharOuFalhar(cidadeId);

			cidadeDisassembler.copyToDomainObject(cidadeInputDto, cidadeAtual);
			cidadeService.salvar(cidadeAtual);

			return cidadeAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@DeleteMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
}
