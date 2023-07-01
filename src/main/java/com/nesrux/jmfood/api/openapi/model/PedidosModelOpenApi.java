package com.nesrux.jmfood.api.openapi.model;

import com.nesrux.jmfood.api.model.dto.output.pedido.PedidoResumoModel;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel("PedidosModel")
public class PedidosModelOpenApi extends PageModelOpenApi<PedidoResumoModel> {

}
