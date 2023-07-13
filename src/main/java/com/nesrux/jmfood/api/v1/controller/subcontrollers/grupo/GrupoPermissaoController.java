package com.nesrux.jmfood.api.v1.controller.subcontrollers.grupo;

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

import com.nesrux.jmfood.api.v1.classconversion.assembler.usuario.PermissaoModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.output.permissao.PermissaoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.grupos.GrupoPermissaoControllerOpenapi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.user.Permissao;
import com.nesrux.jmfood.domain.service.CadastroGrupoService;
import com.nesrux.jmfood.domain.service.CadastroPermissaoService;

@RestController
@RequestMapping(path = "/v1/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenapi {
	@Autowired
	private CadastroGrupoService grupoService;

	@Autowired
	private CadastroPermissaoService permissaoService;

	@Autowired
	private PermissaoModelAssembler assembler;

	@Autowired
	private JmFoodLinks links;

	@GetMapping
	@Override
	public CollectionModel<PermissaoModel> ListarPermissoes(@PathVariable Long grupoId) {

		CollectionModel<PermissaoModel> permissoesModel = assembler
				.toCollectionModel(grupoService.listarPermissoes(grupoId));
		
		permissoesModel.add(links.linkToAssociarPermissão(grupoId, "associar"));
		
		permissoesModel.getContent().forEach(permissao -> {
			permissao.add(links.linktoDesassociarPermissao(grupoId, permissao.getId(), "desassociar"));
		});

		return permissoesModel;
	}

	@GetMapping("/{permissaoId}")
	@Override
	public PermissaoModel buscarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {

		Permissao permissao = permissaoService.acharOuFalhar(permissaoId);

		return assembler.toModel(permissao);
	}

	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> dessassociarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.deassociarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@Override
	public ResponseEntity<Void> associarPermissao(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
		return ResponseEntity.noContent().build();
	}

}
