package com.nesrux.jmfood.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.repository.ProdutoRepository;

@Component
public class CadastroProdutoService {
	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CadastroRestauranteService restauranteService;

	// Teste
	public List<Produto> listarProdutos(Long restauranteId) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

		return restaurante.getProdutos();
	}
	
	//Teste
	@Transactional
	public void salvar(Long restauranteId, Produto produto) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);
		restaurante.adicionarProduto(produto);
		
	}

	public List<Produto> acharTodos(Restaurante restaurante) {
		return repository.findByRestaurante(restaurante);
	}
	
	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
}
