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

import com.nesrux.jmfood.api.classconversion.assembler.FormaPagamentoModelAssembler;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.openapi.controller.restaurante.RestauranteFormaPagamentoControllerOpenApi;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService service;
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoAssembler;

	@GetMapping()
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = service.acharOuFalhar(restauranteId);

		return formaPagamentoAssembler.toCollectionDto(restaurante.getFormasPagamento());

	}

	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		service.desassociarFormaPagamento(restauranteId, formaPagamentoId);
	}

	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarFormaPagamento(@PathVariable Long restauranteId, @PathVariable Long formaPagamentoId) {
		service.associarFormaPagamento(restauranteId, formaPagamentoId);
	}

}
