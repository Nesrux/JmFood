package com.nesrux.jmfood.api.v1.controller.subcontrollers.restaurantes;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.api.v1.classconversion.assembler.produto.FotoProdutoModelAssembler;
import com.nesrux.jmfood.api.v1.model.dto.input.fotoProduto.FotoProdutoInput;
import com.nesrux.jmfood.api.v1.model.dto.output.fotoProduto.FotoProdutoModel;
import com.nesrux.jmfood.api.v1.openapi.controller.produtos.RestauranteFotoProdutoControllerOpenApi;
import com.nesrux.jmfood.core.security.anotations.CheckSecurity;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.service.CadastroFotoProdutoService;
import com.nesrux.jmfood.domain.service.CadastroProdutoService;
import com.nesrux.jmfood.domain.service.FotoStorageService;
import com.nesrux.jmfood.domain.service.FotoStorageService.FotoRecuperada;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteFotoProdutoController implements RestauranteFotoProdutoControllerOpenApi {
	@Autowired
	private CadastroFotoProdutoService service;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private FotoProdutoModelAssembler assembler;
	@Autowired
	private FotoStorageService fotoStorage;

	@CheckSecurity.restaurantes.podeGerenciarCadastro
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestPart(required = true) MultipartFile arquivo, @Valid FotoProdutoInput fotoProdutoInput)
			throws IOException {

		Produto produto = produtoService.acharOuFalhar(produtoId);

		// MultipartFile tipoArquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNome(arquivo.getOriginalFilename());

		FotoProduto fotoSalva = service.salvar(foto, arquivo.getInputStream());

		return assembler.toModel(fotoSalva);
	}

	@GetMapping
	@CheckSecurity.restaurantes.PodeConsultar
	public FotoProdutoModel buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto fotoProduto = service.buscarOuFalhar(restauranteId, produtoId);
		return assembler.toModel(fotoProduto);
	}

	@GetMapping(produces = MediaType.ALL_VALUE)
	@CheckSecurity.restaurantes.PodeConsultar
	public ResponseEntity<?> servirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		try {
			FotoProduto fotoProduto = service.buscarOuFalhar(restauranteId, produtoId);
			MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> mediaTypeAceitas = MediaType.parseMediaTypes(acceptHeader);

			verificarMediaTypeFoto(mediaTypeFoto, mediaTypeAceitas);

			FotoRecuperada fotoRecuperada = fotoStorage.recuperar(fotoProduto.getNome());

			if (fotoRecuperada.temUrl()) {
				return ResponseEntity.status(HttpStatus.FOUND).header(HttpHeaders.LOCATION, fotoRecuperada.getUrl())
						.build();
			} else {
				return ResponseEntity.ok().contentType(mediaTypeFoto)
						.body(new InputStreamResource(fotoRecuperada.getInputStream()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping
	@CheckSecurity.restaurantes.podeGerenciarCadastro
	public void excluirFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		FotoProduto fotoProduto = service.buscarOuFalhar(restauranteId, produtoId);
		fotoStorage.remover(fotoProduto.getNome());
		service.apagarFoto(fotoProduto);
	}

	private void verificarMediaTypeFoto(MediaType mediaTypeFoto, List<MediaType> mediaTypeAceitas)
			throws HttpMediaTypeNotAcceptableException {
		boolean compativel = mediaTypeAceitas.stream()
				.anyMatch(mediaTypeAceita -> mediaTypeAceita.isCompatibleWith(mediaTypeFoto));

		if (!compativel) {
			throw new HttpMediaTypeNotAcceptableException(mediaTypeAceitas);
		}
	}

}
