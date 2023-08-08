package com.nesrux.jmfood.api.v1.controller.main;

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

import com.nesrux.jmfood.api.v1.classconversion.assembler.endereco.EstadoModelAssembler;
import com.nesrux.jmfood.api.v1.classconversion.dissasembler.EstadoInputDisassembler;
import com.nesrux.jmfood.api.v1.model.dto.input.estado.EstadoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.estado.EstadoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.estados.EstadoControllerOpenapi;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.model.endereco.Estado;
import com.nesrux.jmfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(path = "/v1/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenapi {

	@Autowired
	private CadastroEstadoService estadoService;

	@Autowired
	private EstadoInputDisassembler inputDisassembler;

	@Autowired
	private EstadoModelAssembler outputAssembler;

	@Override
	@GetMapping
	@CheckSecurity.Estados.podeConsultar
	public CollectionModel<EstadoModel> listar() {
		return outputAssembler.toCollectionModel(estadoService.acharTodos());
	}

	@Override
	@GetMapping("/{estadoId}")
	@CheckSecurity.Estados.podeConsultar
	public EstadoModel buscar(@PathVariable Long estadoId) {
		return outputAssembler.toModel(estadoService.acharOuFalhar(estadoId));
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.Estados.podeEditar
	public EstadoModel adicionar(@RequestBody @Valid EstadoInputDto estadoDto) {
		Estado estado = inputDisassembler.toDomainObject(estadoDto);
		estadoService.salvar(estado);

		EstadoModel outputDto = outputAssembler.toModel(estado);

		return outputDto;

	}

	@Override
	@PutMapping("{estadoId}")
	@CheckSecurity.Estados.podeEditar
	public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid EstadoInputDto estado) {
		Estado estadoAtual = estadoService.acharOuFalhar(estadoId);

		// BeanUtils.copyProperties(estado, estadoAtual, "id");
		inputDisassembler.copyTodomainObject(estado, estadoAtual);

		Estado estadoSalvo = estadoService.salvar(estadoAtual);

		return estadoSalvo;

	}

	@Override
	@DeleteMapping("{estadoId}")
	@CheckSecurity.Estados.podeEditar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Long estadoId) {
		estadoService.excluir(estadoId);
	}

}
