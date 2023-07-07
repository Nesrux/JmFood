package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.usuario.UsuarioModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.UsuarioInputDissasembler;
import com.nesrux.jmfood.api.model.dto.input.usuario.TrocarSenhaInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInputAtualizar;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.api.openapi.controller.usuarios.UsuarioControllerOpenApi;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {
	@Autowired
	private CadastroUsuarioService service;
	@Autowired
	private UsuarioInputDissasembler dissasembler;
	@Autowired
	private UsuarioModelAssembler assembler;

	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar() {
		List<Usuario> usuariosList = service.acharTodos();

		return assembler.toCollectionModel(usuariosList);

	}

	@Override
	@GetMapping("{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = service.acharOuFalhar(usuarioId);
		return assembler.toModel(usuario);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput userInput) {
		Usuario user = dissasembler.toDomainObject(userInput);
		service.salvar(user);

		UsuarioModel retornoUser = assembler.toModel(user);

		return retornoUser;
	}

	@Override
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId,
			@RequestBody @Valid UsuarioInputAtualizar usuarioInput) {
		Usuario usuario = service.acharOuFalhar(usuarioId);

		dissasembler.copyToDomainObject(usuarioInput, usuario);

		service.salvar(usuario);

		return assembler.toModel(usuario);
	}

	@Override
	@PutMapping("/{usuarioId}/senha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@PathVariable Long usuarioId, @RequestBody @Valid TrocarSenhaInput senhainput) {
		String senhaAtual = senhainput.getSenhaAtual();
		String novaSenha = senhainput.getSenhaNova();

		service.alterarSenha(usuarioId, senhaAtual, novaSenha);
	}

}
