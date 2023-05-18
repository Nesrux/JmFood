package com.nesrux.jmfood.api.controller.subcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.FormaPagamentoOutputAssembler;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService service;
	@Autowired
	private FormaPagamentoOutputAssembler formaPagamentoAssembler;

	@GetMapping()
	public List<FormaPagamentoModel> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = service.acharOuFalhar(restauranteId);

		return formaPagamentoAssembler.toCollectionDto(restaurante.getFormasPagamento());

	}

}
