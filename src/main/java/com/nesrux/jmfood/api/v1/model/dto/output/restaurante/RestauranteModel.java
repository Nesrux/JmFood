package com.nesrux.jmfood.api.v1.model.dto.output.restaurante;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.nesrux.jmfood.api.v1.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.v1.model.dto.output.endereco.EnderecoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "restaurantes")
public class RestauranteModel extends RepresentationModel<RestauranteModel>{
	@ApiModelProperty(value = "ID do restaurante", example = "1", position = 5)
	private Long id;

	@ApiModelProperty(value = "Nome do restaurante", example = "SUper Burguer", position = 10)
	private String nome;

	@ApiModelProperty(value = "taxa do restaurante", example = "10.50", position = 15)
	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

	@ApiModelProperty(value = "Indica se o restaurante esta ativo", example = "true", position = 20)
	private boolean ativo;

	@ApiModelProperty(value = "Indica se o restaurante esta aberto", example = "false", position = 25)
	private boolean aberto;

	private EnderecoModel endereco;

}
