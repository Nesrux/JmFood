package com.nesrux.jmfood.api.model.dto.input.fotoProduto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	private MultipartFile arquivo;
	private String descricao;

}
