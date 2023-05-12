package com.nesrux.jmfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		/*Configuracoes do modelMapper para fazer mapeamento de propriedades com nomes difetenstes*/
		//Nesse caso, não muda em absolutamente nada ter essa config ou não ter, é apenas didatico.
		//modelMapper.createTypeMap(Restaurante.class, RestauranteOutputDTO.class)
			//.addMapping(Restaurante::getTaxaFrete, RestauranteOutputDTO::setTaxaFrete);
		
		
		
		
		return modelMapper;
	}
}
