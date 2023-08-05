package com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes;

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

import com.nesrux.jmfood.api.v1.classconversion.assembler.restaurante.FormaPagamentoModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.restaurante.RestauranteFormaPagamentoControllerOpenApi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroRestauranteService service;
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoAssembler;

	@Autowired
	private JmFoodLinks links;

	@Override
	@GetMapping()
	@CheckSecurity.restaurantes.PodeConsultar
	public CollectionModel<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {

		Restaurante restaurante = service.acharOuFalhar(restauranteId);

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = formaPagamentoAssembler
				.toCollectionModel(restaurante.getFormasPagamento());

		formasPagamentoModel.removeLinks().add(links.linkToRestauranteFormasPagamento(restauranteId))
				.add(links.linktoRestauranteFormaPagamentoAssociar(restauranteId, "associar"));

		formasPagamentoModel.getContent().forEach(formaPagamento -> {
			formaPagamento.add(links.linktoRestauranteFormaPagamentoDesassociacao(restauranteId, formaPagamento.getId(),
					"desassociar"));
		});

		return formasPagamentoModel;
	}

	@Override
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.restaurantes.podeEditar
	public ResponseEntity<Void> desassociarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId) {
		service.desassociarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.restaurantes.podeEditar
	public ResponseEntity<Void> associarFormaPagamento(@PathVariable Long restauranteId,
			@PathVariable Long formaPagamentoId) {
		service.associarFormaPagamento(restauranteId, formaPagamentoId);
		return ResponseEntity.noContent().build();
	}

}
