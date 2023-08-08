package com.nesrux.jmfood.api.v1.controller.main;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.nesrux.jmfood.api.v1.classconversion.assembler.restaurante.FormaPagamentoModelAssembler;
import com.nesrux.jmfood.api.v1.classconversion.dissasembler.FormaPagamentoInputDisassembler;
import com.nesrux.jmfood.api.v1.model.dto.input.formaPagamento.FormaPagamentoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.formaPagamento.FormaPagamentoControllerOpenApi;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping(path = "/v1/formas-pagamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private CadastroFormaPagamentoService service;

	@Autowired
	private FormaPagamentoInputDisassembler disassembler;

	@Autowired
	private FormaPagamentoModelAssembler assembler;

	@Override
	@GetMapping
	@CheckSecurity.FormasPagamento.podeConsultar
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
		// Deep Etags
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = service.ultimaAtualizacao();
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		if (request.checkNotModified(eTag)) {
			return null;
		}

		List<FormaPagamento> formasPagamento = service.acharTodos();

		CollectionModel<FormaPagamentoModel> formasPagamentoModel = assembler.toCollectionModel(formasPagamento);

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(15, TimeUnit.SECONDS).cachePublic()).eTag(eTag)
				.body(formasPagamentoModel);
	}

	@Override
	@GetMapping("/{formaPagamentoID}")
	@CheckSecurity.FormasPagamento.podeConsultar
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoID, ServletWebRequest request) {
		// é esse aqui que eu trnho que fazer na consulta de restaurantes
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

		String eTag = "0";

		OffsetDateTime dataUltimaAtualizacao = service.ultimaAtualizacao(formaPagamentoID);
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		if (request.checkNotModified(eTag)) {
			return null;
		}
		FormaPagamento formaPagamento = service.acharOuFalhar(formaPagamentoID);
		FormaPagamentoModel formaPagamentoModel = assembler.toModel(formaPagamento);

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(15, TimeUnit.SECONDS)).body(formaPagamentoModel);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.FormasPagamento.podeEditar
	public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInputDto inputDto) {
		FormaPagamento formaPagamento = disassembler.toDomainObject(inputDto);

		FormaPagamentoModel outputDto = assembler.toModel(service.salvar(formaPagamento));

		return outputDto;
	}

	@Override
	@PutMapping("/{formaPagamentoId}")
	@CheckSecurity.FormasPagamento.podeEditar
	public FormaPagamentoModel atualizar(@RequestBody @Valid FormaPagamentoInputDto inputDto,
			@PathVariable Long formaPagamentoId) {
		FormaPagamento formaPagamento = service.acharOuFalhar(formaPagamentoId);
		disassembler.copyToDomainObject(inputDto, formaPagamento);
		service.salvar(formaPagamento);

		return assembler.toModel(formaPagamento);
	}

	@Override
	@DeleteMapping("/{formaPagamentoID}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@CheckSecurity.FormasPagamento.podeEditar
	public void excluir(@PathVariable Long formaPagamentoID) {
		service.excluir(formaPagamentoID);
	}

}
