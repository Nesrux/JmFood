package com.nesrux.jmfood.api.model.dto.output.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.nesrux.jmfood.api.model.dto.output.endereco.EnderecoModel;
import com.nesrux.jmfood.api.model.dto.output.formaPagamento.FormaPagamentoModel;
import com.nesrux.jmfood.api.model.dto.output.itemPedido.ItemPedidoModel;
import com.nesrux.jmfood.api.model.dto.output.restaurante.RestauranteResumoModel;
import com.nesrux.jmfood.api.model.dto.output.usuario.UsuarioModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoModel {
	private String codigo;

	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	private String statusPedido;
	
	private UsuarioModel cliente;
	private RestauranteResumoModel restaurante;
	private EnderecoModel endereco;
	private FormaPagamentoModel formaPagamento;
	private List<ItemPedidoModel> itens = new ArrayList<>();

}
