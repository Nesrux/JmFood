package com.nesrux.jmfood.api.controller.subcontrollers.usuarios;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.GrupoModelAssembler;
import com.nesrux.jmfood.api.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController {
	@Autowired
	private CadastroUsuarioService usuarioService;
	
	@Autowired
	private GrupoModelAssembler grupoAssembler;

	@GetMapping
	public List<GrupoModel> listarGruposUsuario(@PathVariable Long usuarioId) {
		Set<Grupo> grupos = usuarioService.listarGruposDoUsuario(usuarioId);
		
		
		return grupoAssembler.toCollectionDto(grupos);
	}

	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associarGrupo(usuarioId, grupoId);
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociarGrupo(usuarioId, grupoId);
	}
}
