package com.nesrux.jmfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteOutputDto;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		/*Configuracoes do modelMapper para fazer mapeamento de propriedades com nomes difetenstes*/
		//Nesse caso, não muda em absolutamente nada ter essa config ou não ter, é apenas didatico.
		modelMapper.createTypeMap(Restaurante.class, RestauranteOutputDto.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteOutputDto::setTaxaFrete);
		
		
		
		
		return modelMapper;
	}
}
