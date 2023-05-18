package com.nesrux.jmfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.ProdutoNaoEncontradoException;
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

	public Produto acharOuFalhar(Long restauranteId, Long produtoId) {
		Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);
		Long restId = restaurante.getId();
		return repository.findById(restId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
	}

	public List<Produto> acharTodos(Restaurante restaurante) {
		return repository.findByRestaurante(restaurante);
	}

	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}
}
