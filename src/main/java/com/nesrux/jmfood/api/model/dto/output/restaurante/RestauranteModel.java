package com.nesrux.jmfood.api.model.dto.output.restaurante;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonView;
import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.model.dto.output.endereco.EnderecoModel;
import com.nesrux.jmfood.api.model.dto.view.RestauranteView;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * Classe de saida de dados para controller de Restaurante
 */
public class RestauranteModel {
	@JsonView({RestauranteView.resumo.class , RestauranteView.apenasNome.class})
	@ApiModelProperty(value = "ID do restaurante", example = "1")
	private Long id;

	@JsonView({RestauranteView.resumo.class , RestauranteView.apenasNome.class})
	@ApiModelProperty(value = "Nome do restaurante", example = "SUper Burguer")
	private String nome;

	@JsonView(RestauranteView.resumo.class)
	@ApiModelProperty(value = "taxa do restaurante", example = "1")
	private BigDecimal taxaFrete;

	@JsonView(RestauranteView.resumo.class)
	private CozinhaModel cozinha;

	private boolean ativo;
	private boolean aberto;
	private EnderecoModel endereco;

}
