package com.nesrux.jmfood.api.model.dto.output.produto;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoModel extends RepresentationModel<ProdutoModel>{
	@ApiModelProperty(example = "1", position = 1)
	private Long id;

	@ApiModelProperty(example = "Frango caipira", position = 5)
	private String nome;

	@ApiModelProperty(example = "Frango caipira com molho agridoce", position = 10)
	private String descricao;

	@ApiModelProperty(example = "23.50", position = 15)
	private BigDecimal preco;

	@ApiModelProperty(example = "true", position = 20)
	private boolean ativo;

}
