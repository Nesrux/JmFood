package com.nesrux.jmfood.api.v1.openapi.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PageModelOpenApi {
		
	@ApiModelProperty(example = "10", value = "Quantidade de registros por página")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "total de registros")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value= "numero da página (começa em 0)")
	private Long number;


}
