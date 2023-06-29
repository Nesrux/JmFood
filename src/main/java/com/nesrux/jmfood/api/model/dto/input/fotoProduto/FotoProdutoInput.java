package com.nesrux.jmfood.api.model.dto.input.fotoProduto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.nesrux.jmfood.core.validation.annotations.FileContentType;
import com.nesrux.jmfood.core.validation.annotations.FileSize;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInput {
	@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "60KB")
	@FileContentType(allowed = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
	private MultipartFile arquivo;
	
	@NotBlank
	@ApiModelProperty(value = "Descrição da foto dod produto", required = true)
	private String descricao;
	
	

}
