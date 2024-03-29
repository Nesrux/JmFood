package com.nesrux.jmfood.api.v1.model.dto.input.pedido;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.nesrux.jmfood.api.v1.model.dto.input.endereco.EnderecoInputDto;
import com.nesrux.jmfood.api.v1.model.dto.input.formaPagamento.FormaPagamentoID;
import com.nesrux.jmfood.api.v1.model.dto.input.itens.ItemPedidoInput;
import com.nesrux.jmfood.api.v1.model.dto.input.restaurante.RestauranteIdInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoInputDto {
	
	@Valid
	@NotNull
	private RestauranteIdInput restaurante;
	
	@Valid
	@NotNull
	private FormaPagamentoID formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInputDto enderecoEntrega;
	
	@Valid
	@NotNull
	@Size(min = 1)
	private List<ItemPedidoInput> itens;

}
