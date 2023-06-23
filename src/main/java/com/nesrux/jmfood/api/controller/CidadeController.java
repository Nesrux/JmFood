package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.CidadeModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.CidadeInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.cidade.CidadeInputDto;
import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeModel;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.EstadoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.service.CadastroCidadeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "Cidades")
@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cidadeService;
	@Autowired
	private CidadeInputDisassembler cidadeDisassembler;
	@Autowired
	private CidadeModelAssembler cidadeAssembler;

	@ApiOperation("Listagem de cidades")
	@GetMapping
	public List<CidadeModel> listar() {
		return cidadeAssembler.toCollectionDto(cidadeService.acharTodas());
	}

	@ApiOperation("Busca de cidades")
	@GetMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.OK)
	public CidadeModel buscar(@ApiParam(value = "Id de uma Cidade", example = "1") @PathVariable Long cidadeId) {
		Cidade cidade = cidadeService.acharOuFalhar(cidadeId);

		return cidadeAssembler.toModel(cidade);
	}

	@ApiOperation("Cadastro de cidades")
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModel adicionar(
			@ApiParam(name = "copo", value = "Representação de uma cidade") @RequestBody @Valid CidadeInputDto cidadeInputDto) {
		try {
			Cidade cidade = cidadeDisassembler.toDomainObject(cidadeInputDto);
			cidadeService.salvar(cidade);

			return cidadeAssembler.toModel(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Atualização de cidades")
	@PutMapping("/{cidadeId}")
	public CidadeModel atualizar(@ApiParam(value = "Id de uma Cidade", example = "1") @PathVariable Long cidadeId,
			@ApiParam(name = "copo", value = "Representação de uma cidade") @RequestBody @Valid CidadeInputDto cidadeInputDto) {
		try {
			Cidade cidadeAtual = cidadeService.acharOuFalhar(cidadeId);

			cidadeDisassembler.copyToDomainObject(cidadeInputDto, cidadeAtual);
			cidadeService.salvar(cidadeAtual);

			return cidadeAssembler.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@ApiOperation("Exclusão de cidades")
	@DeleteMapping("{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@ApiParam(value = "Id de uma Cidade", example = "1") @PathVariable Long cidadeId) {
		cidadeService.excluir(cidadeId);
	}
}
