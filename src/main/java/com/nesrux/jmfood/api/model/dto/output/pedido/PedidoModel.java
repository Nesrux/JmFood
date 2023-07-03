package com.nesrux.jmfood.api.model.dto.output.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.nesrux.jmfood.api.model.dto.output.endereco.EnderecoModel;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.model.dto.output.itemPedido.ItemPedidoModel;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Relation(collectionRelation = "Pedidos")
public class PedidoModel extends RepresentationModel<PedidoModel> {
	@ApiModelProperty(example = "7ab753e5-1e1f-47c2-8f9d-aa611b7138c3", position = 5)
	private String codigo;
	
	@ApiModelProperty(example = "250", position = 10)
	private BigDecimal subtotal;
	
	@ApiModelProperty(example = "25.75", position = 15)
	private BigDecimal taxaFrete;
	
	@ApiModelProperty(example = "275.75", position = 20)
	private BigDecimal valorTotal;
	
	@ApiModelProperty(example = "2023-05-25T12:00:58Z", position = 25)
	private OffsetDateTime dataCriacao;
	
	@ApiModelProperty(example = "2023-05-25T12:00:58Z", position = 30)
	private OffsetDateTime dataConfirmacao;

	@ApiModelProperty(example = "2023-05-25T12:00:58Z", position = 35)
	private OffsetDateTime dataCancelamento;

	@ApiModelProperty(example = "2023-05-25T12:00:58Z", position = 40)
	private OffsetDateTime dataEntrega;
	
	@ApiModelProperty(example = "ENTREGUE", position = 45)
	private String statusPedido;

	private UsuarioModel cliente;
	private RestauranteResumoModel restaurante;
	private EnderecoModel endereco;
	private FormaPagamentoModel formaPagamento;
	private List<ItemPedidoModel> itens = new ArrayList<>();

}
