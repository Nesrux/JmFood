package com.nesrux.jmfood.api.controller.subcontrollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.ProdutoModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.ProdutoInputDisassembler;
import com.nesrux.jmfood.api.model.dto.input.produto.ProdutoInputDto;
import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroProdutoService;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {
	@Autowired
	private CadastroProdutoService service;
	@Autowired
	private ProdutoInputDisassembler disassembler;
	@Autowired
	private ProdutoModelAssembler assembler;
	@Autowired
	private CadastroRestauranteService restauranteService;

	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Long restauranteId) {

		return assembler.toCollectionDto(service.listarProdutos(restauranteId));

	}

	@GetMapping("/{produtoId}")
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = service.acharOuFalhar(restauranteId, produtoId);

		return assembler.toModel(produto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel salvar(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoInputDto inputDto) {
		Produto produto = disassembler.toDomainObject(inputDto);
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		produto.setRestaurante(restaurante);

		service.salvar(produto);

		return assembler.toModel(produto);
	}

	@PutMapping("/{produtoId}")
	public ProdutoModel atualizar(@PathVariable Long produtoId,@Valid @RequestBody ProdutoInputDto inputDto) {
		Produto produto = service.acharOuFalhar(produtoId);
		disassembler.copyToDomainObject(inputDto, produto);
		service.salvar(produto);

		return assembler.toModel(produto);
	}

	@GetMapping("/test")
	public List<ProdutoModel> listarDiferente(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		return assembler.toCollectionDto(restaurante.getProdutos());

	}

}
