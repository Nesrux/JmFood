package com.nesrux.jmfood.api.controller.subcontrollers.restaurantes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.UsuarioModelAssembler;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;
import com.nesrux.jmfood.api.openapi.controller.restaurante.RestauranteUsuarioControllerOpenApi;
import com.nesrux.jmfood.domain.model.user.Usuario;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {
	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private UsuarioModelAssembler usuarioAssembler;

	@Override
	@GetMapping
	public List<UsuarioModel> listarFuncionariosRestaurante(@PathVariable Long restauranteId) {
		List<Usuario> usuarios = restauranteService.listarUsuarios(restauranteId);

		return usuarioAssembler.toCollectionModel(usuarios);
	}

	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarFuncionarios(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarFuncionario(restauranteId, usuarioId);
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFuncionarios(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.desassociarFuncionario(restauranteId, usuarioId);
	}

}
