package com.nesrux.jmfood.api.v1.controller;

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

import com.nesrux.jmfood.api.v1.classconversion.assembler.usuario.GrupoModelAssembler;
import com.nesrux.jmfood.api.v1.classconversion.dissasembler.GrupoInputDisassembler;
import com.nesrux.jmfood.api.v1.model.dto.input.grupo.GrupoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.grupos.GrupoControllerOpenApi;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController implements GrupoControllerOpenApi {
	@Autowired
	private CadastroGrupoService service;
	@Autowired
	private GrupoInputDisassembler disassembler;
	@Autowired
	private GrupoModelAssembler assembler;

	@Override
	@GetMapping
	public CollectionModel<GrupoModel> listar() {
		return assembler.toCollectionModel(service.acharTodos());
	}

	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = service.acharOuFalahar(grupoId);

		return assembler.toModel(grupo);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel salvar(@Valid @RequestBody GrupoInputDto inpDto) {
		Grupo grupo = disassembler.toDomainObject(inpDto);
		service.salvar(grupo);

		return assembler.toModel(grupo);
	}

	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long grupoId) {
		service.excluir(grupoId);
	}

	@Override
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@Valid @RequestBody GrupoInputDto inputDto, @PathVariable Long grupoId) {
		Grupo grupo = service.acharOuFalahar(grupoId);
		disassembler.copyToDomainObject(inputDto, grupo);

		service.salvar(grupo);

		return assembler.toModel(grupo);

	}

}
