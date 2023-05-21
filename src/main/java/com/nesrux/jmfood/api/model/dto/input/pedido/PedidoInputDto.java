package com.nesrux.jmfood.api.model.dto.input.pedido;

import java.util.List;

import com.nesrux.jmfood.api.model.dto.input.endereco.EnderecoInputDto;
import com.nesrux.jmfood.api.model.dto.input.formaPagamento.FormaPagamentoInputDto;
import com.nesrux.jmfood.api.model.dto.input.itens.ItemPedidoInput;
import com.nesrux.jmfood.api.model.dto.input.restaurante.RestauranteInputDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {

	private RestauranteInputDto restaurante;
	private FormaPagamentoInputDto formaPagamento;
	private EnderecoInputDto enderecoEntrega;
	private List<ItemPedidoInput> itens;

}
