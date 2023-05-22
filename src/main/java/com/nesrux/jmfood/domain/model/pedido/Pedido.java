package com.nesrux.jmfood.domain.model.pedido;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.nesrux.jmfood.domain.model.endereco.Endereco;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.model.user.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Pedido {
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@Embedded
	private Endereco endereco;
	@Enumerated(EnumType.STRING)
	private StatusPedido status;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal() {
		getItens().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItens().stream()
				.map(item -> item.getPrecoTotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	
		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	/*
	 * a anotação Embedded faz com que a classe dessa propriedade faça parte da
	 * mesma tabela dessa entidade, ou seja, ajuda na organização do código e ajuda
	 * no re-aproveitamento do código, na hora da persistencia dos dados dessa
	 * entidade, todas as propriedades dessa classe vao ser encorporadas a tabela
	 * dessa entidade.
	 */

	/*
	 * Na hora da persistencia dos dados dessa entidade, a
	 * anotação @cretionTimeStanp vai "Ativar essa propriedade" e colocar uma data
	 * no banco de dados, ela só ativa uma vez
	 */
}
