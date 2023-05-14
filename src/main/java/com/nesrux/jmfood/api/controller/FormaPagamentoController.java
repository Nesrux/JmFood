package com.nesrux.jmfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.FormaPagamentoOutputAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.FormaPagamentoInputDisassembler;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoOutputDto;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

	@Autowired
	private CadastroFormaPagamentoService service;

	@Autowired
	private FormaPagamentoInputDisassembler disassembler;

	@Autowired
	private FormaPagamentoOutputAssembler assembler;

	@GetMapping
	public List<FormaPagamentoOutputDto> listar() {
		return assembler.toCollectionDto(service.acharTodos());
	}
	
	@GetMapping("/{formaPagamentoID}")
	public FormaPagamentoOutputDto buscar(@PathVariable Long formaPagamentoID) {
		FormaPagamento formaPagamento = service.acharOuFalhar(formaPagamentoID);
		
		return assembler.toModel(formaPagamento);
	}

}
