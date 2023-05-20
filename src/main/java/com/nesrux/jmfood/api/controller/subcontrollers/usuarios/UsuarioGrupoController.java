package com.nesrux.jmfood.api.controller.subcontrollers.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.GrupoModelAssembler;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	@Autowired
	private CadastroUsuarioService usuarioService;

	@Autowired
	private GrupoModelAssembler grupoAssembler;

	@GetMapping
	public List<Grupo> listarGruposUsuario(@PathVariable Long usuarioId) {
//		List<Grupo> grupos = usuarioService.listarGrupos(usuarioId);

		Usuario usuario = usuarioService.acharOuFalhar(usuarioId);

		return usuario.getGrupos().stream().toList();
	}

	@PutMapping
	public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associarGrupo(usuarioId, grupoId);
	}

	@DeleteMapping
	public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociarGrupo(usuarioId, grupoId);
	}
}
