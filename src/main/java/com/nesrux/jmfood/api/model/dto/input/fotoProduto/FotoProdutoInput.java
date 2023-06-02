package com.nesrux.jmfood.api.model.dto.input.fotoProduto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.core.validation.annotations.FileContentType;
import com.nesrux.jmfood.core.validation.annotations.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {

	@NotNull
	@FileSize(max = "30KB")
	@FileContentType(allowed = {MediaType.IMAGE_PNG_VALUE})
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
	
	

}
