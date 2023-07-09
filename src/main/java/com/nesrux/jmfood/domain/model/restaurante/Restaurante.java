package com.nesrux.jmfood.domain.model.restaurante;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.nesrux.jmfood.domain.model.endereco.Endereco;
import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;
import com.nesrux.jmfood.domain.model.pedido.Produto;
import com.nesrux.jmfood.domain.model.user.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	// TODO estudar sobre lazy loading e Eager loagind

	// OBS: quanto termina com ToOne o padrão do sping é botar EAGER loading
	// quanto termina co =m toMany o padrão do spirng é botar LAZY loading

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;

	@Column(name = "ativo")
	private boolean ativo = Boolean.TRUE;

	@Column(name = "aberto")
	private boolean aberto = Boolean.FALSE;

	@ManyToOne // (fetch = FetchType.LAZY)
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	@Embedded
	private Endereco endereco;

	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataCadastro;

	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private OffsetDateTime dataAtualizacao;

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
	private Set<FormaPagamento> formasPagamento = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "restaurante_usuario_responsavel", joinColumns = @JoinColumn(name = "restaurante_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private Set<Usuario> usuarioResponsavel = new HashSet<>();

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<>();

	public Restaurante() {
	}

	public void ativar() {
		setAtivo(true);
	}

	public void desativar() {
		setAtivo(false);
	}

	public void abrir() {
		setAberto(true);
	}

	public void fechar() {
		setAberto(false);
	}

	public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().remove(formaPagamento);
	}

	public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().add(formaPagamento);

	}
	public boolean aceitaFormaPagamento(FormaPagamento formaPagamento) {
		return getFormasPagamento().contains(formaPagamento);
	}
	
	public boolean naoAceitaFormaPagamento(FormaPagamento formaPagamento) {
		return !aceitaFormaPagamento(formaPagamento);
	}

	public boolean associarUsuario(Usuario usuario) {
		return getUsuarioResponsavel().add(usuario);
	}

	public boolean desassociarUsuario(Usuario usuario) {
		return getUsuarioResponsavel().remove(usuario);
	}
	public boolean verificaEnderecoNulo() {
		return getEndereco() != null && getEndereco().getCidade() != null;
	}

}
