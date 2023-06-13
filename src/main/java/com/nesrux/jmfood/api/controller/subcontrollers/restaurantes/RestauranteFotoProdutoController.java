package com.nesrux.jmfood.api.controller.subcontrollers.restaurantes;

import java.io.IOException;
import java.io.InputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.api.classconversion.assembler.FotoProdutoModelAssembler;
import com.nesrux.jmfood.api.model.dto.input.fotoProduto.FotoProdutoInput;
import com.nesrux.jmfood.api.model.dto.output.fotoProduto.FotoProdutoModel;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.service.CadastroFotoProdutoService;
import com.nesrux.jmfood.domain.service.CadastroProdutoService;
import com.nesrux.jmfood.domain.service.FotoStorageService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController {
	@Autowired
	private CadastroFotoProdutoService service;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private FotoProdutoModelAssembler assembler;
	@Autowired
	private FotoStorageService fotoStorage;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) throws IOException {

		Produto produto = produtoService.acharOuFalhar(produtoId);

		MultipartFile tipoArquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(tipoArquivo.getContentType());
		foto.setTamanho(tipoArquivo.getSize());
		foto.setNome(tipoArquivo.getOriginalFilename());

		FotoProduto fotoSalva = service.salvar(foto, tipoArquivo.getInputStream());

		return assembler.toModel(fotoSalva);
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto fotoProduto = service.buscarOuFalhar(restauranteId, produtoId);
		return assembler.toModel(fotoProduto);
	}

	@GetMapping(produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_JPEG_VALUE })
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId) {
		try {
			FotoProduto fotoProduto = service.buscarOuFalhar(restauranteId, produtoId);
			InputStream inputStram = fotoStorage.recuperar(fotoProduto.getNome());

			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_JPEG)
					.body(new InputStreamResource(inputStram));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
