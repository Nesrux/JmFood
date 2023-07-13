package com.nesrux.jmfood.api.v1.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel("Paginação")
@Getter
@Setter
public class PropriedadesPaginacaoModel {

	@ApiModelProperty(example = "1", value = "Número da página (começa em 0)")
	private int page;

	@ApiModelProperty(example = "5", value = "por padrão é 20")
	private int size;

	@ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
	private List<String> sort;
}
