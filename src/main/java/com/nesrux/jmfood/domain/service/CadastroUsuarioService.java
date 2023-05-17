package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.UsuarioNaoEncontradoException;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.UsuarioRepository;

@Component
public class CadastroUsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public List<Usuario> acharTodos() {
		return repository.findAll();
	}

	public Usuario acharOuFalhar(Long usuarioId) {
		return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	public Usuario Salvar(Usuario usuario) {
		return repository.save(usuario);
	}

	public String acharEmail(Long usuarioId) {
		return repository.findEmailByID(usuarioId);
	}

	public String acharSenha(Long usuarioId) {
		return repository.findEmailByID(usuarioId);
	}

}
