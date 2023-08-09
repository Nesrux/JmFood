package com.nesrux.jmfood.api.v1.controller.subcontrollers.usuarios;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.classconversion.assembler.usuario.GrupoModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.output.grupo.GrupoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.usuarios.UsuarioGrupoControllerOpenApi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.model.user.Grupo;
import com.nesrux.jmfood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping(path = "/v1/usuarios/{usuarioId}/grupos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {
	@Autowired
	private CadastroUsuarioService usuarioService;
	@Autowired
	private GrupoModelAssembler grupoAssembler;
	@Autowired
	private JmFoodLinks links;

	@Override
	@GetMapping
	@CheckSecurity.UsuariosGruposPermissoes.PodeConsultar
	public CollectionModel<GrupoModel> listarGruposUsuario(@PathVariable Long usuarioId) {
		Set<Grupo> grupos = usuarioService.listarGruposDoUsuario(usuarioId);
		CollectionModel<GrupoModel> gruposUsuarioModel = grupoAssembler.toCollectionModel(grupos)
				.removeLinks().add(links.linkToGruposUsuario(usuarioId))
				.add(links.linkToAssociarGrupo(usuarioId, "associar"));

		gruposUsuarioModel.getContent().forEach(grupo -> {
			grupo.add(links.linkToDesassociargrupo(usuarioId, usuarioId, "desassociar"));
		});

		return gruposUsuarioModel;
	}

	@Override
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.UsuariosGruposPermissoes.PodeAlterarUsuario
	public ResponseEntity<Void> desassociarGrupo(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociarGrupo(usuarioId, grupoId);

		return ResponseEntity.noContent().build();
	}
}
