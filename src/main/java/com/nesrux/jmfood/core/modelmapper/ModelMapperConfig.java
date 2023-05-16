package com.nesrux.jmfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nesrux.jmfood.api.model.dto.output.endereco.EnderecoModel;
import com.nesrux.jmfood.domain.model.endereco.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

		/*
		 * Configuracoes do modelMapper para fazer mapeamento de propriedades com nomes
		 * difetenstes
		 */
		// Nesse caso, não muda em absolutamente nada ter essa config ou não ter, é
		// apenas didatico.
		// modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
		// .addMapping(Restaurante::getTaxaFrete, RestauranteModel::setTaxaFrete);

		var enderecoModelTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
	
		enderecoModelTypeMap.<String>addMapping(src -> src.getCidade().getEstado().getNome(),
				(dest, value) -> dest.getCidade().setEstado(value));
	
		return modelMapper;
	}
}
