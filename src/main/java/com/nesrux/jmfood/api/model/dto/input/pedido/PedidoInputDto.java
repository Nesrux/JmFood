package com.nesrux.jmfood.api.model.dto.input.pedido;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.nesrux.jmfood.api.model.dto.input.endereco.EnderecoInputDto;
import com.nesrux.jmfood.api.model.dto.input.formaPagamento.FormaPagamentoID;
import com.nesrux.jmfood.api.model.dto.input.itens.ItemPedidoInput;
import com.nesrux.jmfood.api.model.dto.input.restaurante.RestauranteInputDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {
	
	@Valid
	@NotNull
	private RestauranteInputDto restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoID formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInputDto enderecoEntrega;
	
	@Valid
	@NotNull
	private List<ItemPedidoInput> itens;

}
