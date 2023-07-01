package com.nesrux.jmfood.api.springFox.model;

import com.nesrux.jmfood.api.model.dto.output.cozinha.CozinhaModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("CozinhasModel")
@Getter
@Setter
public class CozinhasModelOpenApi extends PageModelOpenApi<CozinhaModel>  {

}
