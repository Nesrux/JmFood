package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.FormaPagamentoOutputAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.FormaPagamentoInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.formaPagamento.FormaPagamentoInputDto;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
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
	public List<FormaPagamentoModel> listar() {
		return assembler.toCollectionDto(service.acharTodos());
	}

	@GetMapping("/{formaPagamentoID}")
	public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoID) {
		FormaPagamento formaPagamento = service.acharOuFalhar(formaPagamentoID);

		return assembler.toModel(formaPagamento);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInputDto inputDto) {
		FormaPagamento formaPagamento = disassembler.toDomainObject(inputDto);

		FormaPagamentoModel outputDto = assembler.toModel(service.salvar(formaPagamento));

		return outputDto;
	}

	@PutMapping("/{formaPagamentoId}")
	public FormaPagamentoModel atualizar(@RequestBody FormaPagamentoInputDto inputDto,
			@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = service.acharOuFalhar(formaPagamentoId);
		disassembler.copyToDomainObject(inputDto, formaPagamento);
		service.salvar(formaPagamento);

		return assembler.toModel(formaPagamento);
	}

	@DeleteMapping("/{formaPagamentoID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long formaPagamentoID) {
		service.excluir(formaPagamentoID);
	}

}
