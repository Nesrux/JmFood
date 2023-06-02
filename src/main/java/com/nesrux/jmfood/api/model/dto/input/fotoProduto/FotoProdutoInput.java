package com.nesrux.jmfood.api.model.dto.input.fotoProduto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.core.validation.annotations.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "5KB")
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;

}
