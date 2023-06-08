package com.nesrux.jmfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.repository.ProdutoRepository;
import com.nesrux.jmfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CadastroFotoProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FotoStorageService fotoStorageService;

	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteid = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeArquivo = fotoStorageService.gerarNomeArquivo(foto.getNome());
		Optional<FotoProduto> FotoExistente = produtoRepository.findFotoById(restauranteid, produtoId);
		if (FotoExistente.isPresent()) {
			produtoRepository.delete(FotoExistente.get());
		}
		foto.setNome(nomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();

		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNome())
				.inputStream(dadosArquivo).build();

		fotoStorageService.armazenar(novaFoto);

		return foto;
	}

}
