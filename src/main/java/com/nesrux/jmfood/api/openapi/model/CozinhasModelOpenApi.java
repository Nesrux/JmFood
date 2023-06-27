package com.nesrux.jmfood.api.openapi.model;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi  {
	private List<CozinhaModel> content;
	
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "total de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value= "numero da página (começa em 0)")
	private Long number;

}
