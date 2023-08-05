package com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.v1.classconversion.assembler.produto.ProdutoModelAssembler;
import com.nesrux.jmfood.api.v1.classconversion.dissasembler.ProdutoInputDisassembler;
import com.nesrux.jmfood.api.v1.model.dto.input.produto.ProdutoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.output.produto.ProdutoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.restaurante.RestauranteProdutoControllerOpenApi;
import com.nesrux.jmfood.api.v1.utils.JmFoodLinks;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroProdutoService;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

	@Autowired
	private CadastroProdutoService service;
	@Autowired
	private ProdutoInputDisassembler disassembler;
	@Autowired
	private ProdutoModelAssembler assembler;
	@Autowired
	private CadastroRestauranteService restauranteService;
	@Autowired
	private JmFoodLinks links;

	@Override
	@GetMapping
	@CheckSecurity.restaurantes.PodeConsultar
	public CollectionModel<ProdutoModel> listar(@PathVariable Long restauranteId,
			@RequestParam(required = false, defaultValue = "false") Boolean incluirInativos) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);
		
		CollectionModel<ProdutoModel> produtosModel = null;
		
		if (incluirInativos) {
			produtosModel = assembler.toCollectionModel(service.acharTodos(restaurante));

		} else {
			produtosModel = assembler.toCollectionModel(service.acharTodosAtivos(restaurante));
		}
		return produtosModel.add(links.linkToProdutos(restauranteId));

	}

	@Override
	@GetMapping("/{produtoId}")
	@CheckSecurity.restaurantes.PodeConsultar
	public ProdutoModel buscar(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		Produto produto = service.acharOuFalhar(restauranteId, produtoId);

		return assembler.toModel(produto);
	}

	@Override
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@CheckSecurity.restaurantes.podeEditar
	public ProdutoModel salvar(@PathVariable Long restauranteId, @Valid @RequestBody ProdutoInputDto inputDto) {
		Produto produto = disassembler.toDomainObject(inputDto);
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		produto.setRestaurante(restaurante);

		service.salvar(produto);

		return assembler.toModel(produto);
	}

	@Override
	@PutMapping("/{produtoId}")
	@CheckSecurity.restaurantes.podeEditar
	public ProdutoModel atualizar(@PathVariable Long produtoId, @Valid @RequestBody ProdutoInputDto inputDto) {
		Produto produto = service.acharOuFalhar(produtoId);
		disassembler.copyToDomainObject(inputDto, produto);
		service.salvar(produto);

		return assembler.toModel(produto);
	}

}
