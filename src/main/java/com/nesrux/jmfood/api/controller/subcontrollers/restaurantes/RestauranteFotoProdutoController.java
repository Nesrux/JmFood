package com.nesrux.jmfood.api.controller.subcontrollers.restaurantes;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.api.classconversion.assembler.FotoProdutoModelAssembler;
import com.nesrux.jmfood.api.model.dto.input.fotoProduto.FotoProdutoInput;
import com.nesrux.jmfood.api.model.dto.output.fotoProduto.FotoProdutoModel;
import com.nesrux.jmfood.domain.model.pedido.FotoProduto;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.service.CadastroFotoProdutoService;
import com.nesrux.jmfood.domain.service.CadastroProdutoService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteFotoProdutoController {
	@Autowired
	private CadastroFotoProdutoService service;
	@Autowired
	private CadastroProdutoService produtoService;
	@Autowired
	private FotoProdutoModelAssembler assembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
			@Valid FotoProdutoInput fotoProdutoInput) {

		Produto produto = produtoService.acharOuFalhar(produtoId);

		MultipartFile tipoArquivo = fotoProdutoInput.getArquivo();

		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(tipoArquivo.getContentType());
		foto.setTamanho(tipoArquivo.getSize());
		foto.setNome(tipoArquivo.getOriginalFilename());

		FotoProduto fotoSalva = service.salvar(foto);

		return assembler.toModel(fotoSalva);
	}

}
