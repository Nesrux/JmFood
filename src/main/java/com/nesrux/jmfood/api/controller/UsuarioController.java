package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.UsuarioOutputAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.UsuarioInputDissasembler;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInput;
import com.nesrux.jmfood.api.model.dto.input.usuario.UsuarioInputAtualizar;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private CadastroUsuarioService service;
	@Autowired
	private UsuarioInputDissasembler dissasembler;
	@Autowired
	private UsuarioOutputAssembler assembler;

	@GetMapping
	public List<UsuarioModel> listar() {
		return assembler.toCollectionDto(service.acharTodos());
	}

	@GetMapping("{usuarioId}")
	public UsuarioModel buscar(@PathVariable Long usuarioId) {
		Usuario usuario = service.acharOuFalhar(usuarioId);
		return assembler.toModel(usuario);
	}

	@PostMapping
	public UsuarioModel salvar(@RequestBody @Valid UsuarioInput userInput) {
		Usuario user = dissasembler.toDomainObject(userInput);
		service.salvar(user);

		UsuarioModel retornoUser = assembler.toModel(user);

		return retornoUser;
	}
	@PutMapping("/{usuarioId}")
	public UsuarioModel atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputAtualizar usuarioInput) {
		Usuario usuario = service.acharOuFalhar(usuarioId);
		
		dissasembler.copyToDomainObject(usuarioInput, usuario);
		
		service.salvar(usuario);
		
		return assembler.toModel(usuario);
	}

}
