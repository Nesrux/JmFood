package com.nesrux.jmfood.api.controller.subcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.api.classconversion.assembler.ProdutoModelAssembler;
import com.nesrux.jmfood.api.classconversion.dissasembler.ProdutoInputDisassembler;
import com.nesrux.jmfood.api.model.dto.output.produto.ProdutoModel;
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
	public List<ProdutoModel> listar(@PathVariable Long restauranteId){
		
		return assembler.toCollectionDto(service.listarProdutos(restauranteId));
		
	}
	
	@GetMapping("/test")
	public List<ProdutoModel> listarDiferente(@PathVariable Long restauranteId){
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);
		
		return assembler.toCollectionDto(restaurante.getProdutos());
		
	}


}
