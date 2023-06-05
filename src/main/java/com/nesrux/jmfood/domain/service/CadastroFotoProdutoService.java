package com.nesrux.jmfood.domain.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.repository.ProdutoRepository;

@Service
public class CadastroFotoProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public FotoProduto salvar(FotoProduto foto) {
		Long restauranteid = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();

		Optional<FotoProduto> FotoExistente = produtoRepository.findFotoById(restauranteid, produtoId);
		if (FotoExistente.isPresent()) {
			produtoRepository.delete(FotoExistente.get());
		}

		return produtoRepository.save(foto);
	}

}
