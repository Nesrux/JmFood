package com.nesrux.jmfood.api.model.dto.output.restaurante;

import java.math.BigDecimal;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;
import com.nesrux.jmfood.api.model.dto.output.endereco.EnderecoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/** 
 * Classe de saida de dados para controller de Restaurante 
 */
public class RestauranteModel {
	private Long id;
	private String nome;
	private BigDecimal taxaFrete;
	private boolean ativo;
	
	private CozinhaModel cozinha;
	
	private EnderecoModel endereco;
	

}
