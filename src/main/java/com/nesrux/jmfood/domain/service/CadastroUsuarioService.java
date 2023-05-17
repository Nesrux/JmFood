package com.nesrux.jmfood.domain.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.NegocioException;
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

	@Transactional
	public Usuario salvar(Usuario usuario) {
		repository.detach(usuario);
		
		Optional<Usuario> usuarioExistente = repository.findByEmail(usuario.getEmail());

		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("ja exisre um usuário cadastrado com o email %s", usuario.getEmail()));
		}

		return repository.save(usuario);
	}

	@Transactional
	public void alterarSenha(Long userID, String senhaAtual, String novaSenha) {
		Usuario usuario = acharOuFalhar(userID);

		if (usuario.senhaNaoConhecideCom(senhaAtual)) {
			throw new NegocioException("A senha atual esta inválida, por favor preencha corretamente e tente de novo.");

		}
		usuario.setSenha(novaSenha);
	}
}
