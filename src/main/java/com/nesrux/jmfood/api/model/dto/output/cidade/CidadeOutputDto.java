package com.nesrux.jmfood.api.model.dto.output.cidade;

import com.nesrux.jmfood.api.model.dto.output.estado.EstadoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeOutputDto {
	private Long id;
	
	private String nome;
	
	private EstadoModel estado;
}
