package com.nesrux.jmfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.FotoNaoEncontradaException;
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
		String nomeArquivoExistente = null;
		Optional<FotoProduto> FotoExistente = produtoRepository.findFotoById(restauranteid, produtoId);

		if (FotoExistente.isPresent()) {
			nomeArquivoExistente = FotoExistente.get().getNome();
			produtoRepository.delete(FotoExistente.get());
		}
		foto.setNome(nomeArquivo);
		foto = produtoRepository.save(foto);
		produtoRepository.flush();

		NovaFoto novaFoto = NovaFoto.builder()
				.nomeArquivo(foto.getNome())
				.contentType(foto.getContentType())
				.inputStream(dadosArquivo)
				.build();

		fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

		return foto;
	}

	public FotoProduto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findFotoById(restauranteId, produtoId)
				.orElseThrow(() -> new FotoNaoEncontradaException(restauranteId, produtoId));
	}
	
	public void apagarFoto(FotoProduto fotoProduto) {
		produtoRepository.delete(fotoProduto);
	}

}
