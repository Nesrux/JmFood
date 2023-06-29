package com.nesrux.jmfood.api.model.dto.output.endereco;

import com.nesrux.jmfood.api.model.dto.output.cidade.CidadeResumoModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoModel {
	@ApiModelProperty(value = "cep", example = "1587-852", position = 5)
	private String cep;
	@ApiModelProperty(value = "Logradouro de um endereço", example = "rua do dericantla", position = 10)
	private String logradouro;
	@ApiModelProperty(value = "numero da residencia", example = "35", position = 15)
	private String numero;
	@ApiModelProperty(value = "observações", example = "Perto da escola", position = 20)
	private String complemento;
	@ApiModelProperty(value = "bairro da residencia", example = "São matheus", position = 25)
	private String bairro;

	private CidadeResumoModel cidade;

}
