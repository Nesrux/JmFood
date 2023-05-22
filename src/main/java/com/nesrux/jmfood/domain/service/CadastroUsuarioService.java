package com.nesrux.jmfood.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.UsuarioNaoEncontradoException;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	@Autowired
	private UsuarioRepository repository;

	@Autowired
	private CadastroGrupoService grupoService;

	public List<Usuario> acharTodos() {
		return repository.findAll();
	}

	public Usuario acharOuFalhar(Long usuarioId) {
		return repository.findById(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	public List<Grupo> listarGrupos(Long usuarioId) {
		Usuario usuario = acharOuFalhar(usuarioId);

		return usuario.getGrupos().stream().toList();
	}

	// Para efeitos de teste
	public Set<Grupo> listarGruposDoUsuario(Long usuarioId) {
		Usuario usuario = acharOuFalhar(usuarioId);

		return usuario.getGrupos();
	}
	@Transactional
	public void associarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = acharOuFalhar(usuarioId);
		Grupo grupo = grupoService.acharOuFalahar(grupoId);
		
		usuario.associar(grupo);
	}
	@Transactional
	public void desassociarGrupo(Long usuarioId, Long grupoId) {
		Usuario usuario = acharOuFalhar(usuarioId);
		Grupo grupo = grupoService.acharOuFalahar(grupoId);
		
		usuario.desassociar(grupo);
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
