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

import com.nesrux.jmfood.api.classconversion.assembler.GrupoModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.GrupoInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.grupo.GrupoInputDto;
import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.api.openapi.interfaces.GrupoControllerOpenApi;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController implements GrupoControllerOpenApi {
	@Autowired
	private CadastroGrupoService service;
	@Autowired
	private GrupoInputDisassembler disassembler;
	@Autowired
	private GrupoModelAssembler assembler;

	@GetMapping
	public List<GrupoModel> listar() {
		return assembler.toCollectionDto(service.acharTodos());
	}

	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = service.acharOuFalahar(grupoId);

		return assembler.toModel(grupo);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel salvar(@Valid @RequestBody GrupoInputDto inpDto) {
		Grupo grupo = disassembler.toDomainObject(inpDto);
		service.salvar(grupo);

		return assembler.toModel(grupo);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long grupoId) {
		service.excluir(grupoId);
	}

	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@Valid @RequestBody GrupoInputDto inputDto, @PathVariable Long grupoId) {
		Grupo grupo = service.acharOuFalahar(grupoId);
		disassembler.copyToDomainObject(inputDto, grupo);
		
		service.salvar(grupo);

		return assembler.toModel(grupo);
		
	}

}
