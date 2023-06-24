package com.nesrux.jmfood.api.exceptionHandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ErroApi {
	/*
	 * Uma classe que padroniza as exceptions da api utilzando o padrao RFC 7807
	 * para padronizar todos os erros da API
	 */
	@ApiModelProperty(example = "400", position = 1)
	private Integer status;

	@ApiModelProperty(example = "https://jmfood.com.br/dados-invalidos", position = 5)
	private String type;

	@ApiModelProperty(example = "os dados estão invalidos", position = 10)
	private String title;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 15)
	private String detail;

	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", position = 20)
	private String userMessage;

	@ApiModelProperty(example = "2023-06-24T19:34:53.3309101Z", position = 25)
	private OffsetDateTime timesStamp;

	@ApiModelProperty(value = "Objetos ou campos que geraram o Erro", position = 30)
	private List<Object> objects;

	@ApiModel("Problemas")
	@Getter
	@Builder
	public static class Object {

		@ApiModelProperty(example = "taxaFrete")
		private String nome;

		@ApiModelProperty(example = "taxaFrete é obrigatório")
		private String userMessage;
	}
}
