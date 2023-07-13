package com.nesrux.jmfood.api.v1.model.dto.input.endereco;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.nesrux.jmfood.api.v1.model.dto.input.cidade.CidadeIDInput;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoInputDto {
	@NotBlank
	@ApiModelProperty(example = "08485-130", required = true, position = 5)
	private String cep;
	
	@NotBlank
	@ApiModelProperty(example = "Rua das castanheiras", required = true, position = 10)
	private String logradouro;
	
	@NotBlank
	@ApiModelProperty(example = "N 35", required = true, position = 15)
	private String numero;
	@ApiModelProperty(example = "appto 152 A", position = 20)
	private String complemento;
	
	@NotBlank
	@ApiModelProperty(example = "Itaquera", required = true, position = 25)
	private String bairro;
	
	@NotNull
	@Valid
	private CidadeIDInput cidade;
	
}
