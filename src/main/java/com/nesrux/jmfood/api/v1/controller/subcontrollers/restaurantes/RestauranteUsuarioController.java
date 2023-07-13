package com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes;

import java.util.List;

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

import com.nesrux.jmfood.api.v1.classconversion.assembler.usuario.UsuarioModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.api.v1.openapi.controller.restaurante.RestauranteUsuarioControllerOpenApi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {
	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private UsuarioModelAssembler usuarioAssembler;

	@Autowired
	private JmFoodLinks jmFoodLinks;

	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listarFuncionariosRestaurante(@PathVariable Long restauranteId) {
		List<Usuario> usuarios = restauranteService.usuariosResponsaveis(restauranteId);
		CollectionModel<UsuarioModel> usuarioModelList = usuarioAssembler.toCollectionModel(usuarios);

		usuarioModelList.removeLinks()
				.add(jmFoodLinks.linkToRestauranteResponsaveis(restauranteId)).add(jmFoodLinks.linkToUsuarioAssociar(restauranteId, "associar"));

		usuarioModelList.getContent().forEach(usuario -> {
			usuario.add(jmFoodLinks.linkToUsuarioDesassociar(restauranteId, usuario.getId(), "desassociar"));
		});

		return usuarioModelList;
	}

	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associarFuncionarios(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarFuncionario(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociarFuncionarios(@PathVariable Long restauranteId,
			@PathVariable Long usuarioId) {
		restauranteService.desassociarFuncionario(restauranteId, usuarioId);
		return ResponseEntity.noContent().build();
	}

}
