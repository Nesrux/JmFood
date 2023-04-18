package com.nesrux.jmfood.domain.model.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.nesrux.jmfood.domain.model.endereco.Endereco;
import com.nesrux.jmfood.domain.model.user.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Pedido {
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal subtotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;

	@CreationTimestamp
	private LocalDate dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;

	private StatusPedido statusPedido;

	@Embedded
	private Endereco endereco;

	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;

	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itemPedidos = new ArrayList<>();
}
