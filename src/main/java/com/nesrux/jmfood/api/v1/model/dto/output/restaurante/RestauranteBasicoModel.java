package com.nesrux.jmfood.api.v1.model.dto.output.restaurante;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.nesrux.jmfood.api.v1.model.dto.output.cozinha.CozinhaModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "restaurantes")
public class RestauranteBasicoModel extends RepresentationModel<RestauranteBasicoModel> {

	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "Mano Burguers")
	private String nome;

	@ApiModelProperty(example = "10.50")
	private BigDecimal taxaFrete;

	private CozinhaModel cozinha;

}
