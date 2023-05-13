package com.nesrux.jmfood.api.model.dto.output.restaurante;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** 
 * Classe de saida de dados para controller de Restaurante 
 */
public class RestauranteOutputDto {
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;

	private CozinhaOutputRestaurante cozinha;

}
