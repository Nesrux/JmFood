package com.nesrux.jmfood.domain.model.user;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String senha;

	@Column(nullable = false)
	@UpdateTimestamp
	private OffsetDateTime dataCadastro;

	@ManyToMany
	@JoinTable(name = "usuario_grupos", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "grupo_id"))
	private List<Grupo> grupos = new ArrayList<>();

	// a anotação manyToMany cria uma tabela intermediaria que faz a ponte entre 2
	// tabelas
	// ela precisa de uma segunda anotação que é a @joinTable, que cria essa tabela
	// de intesecção
	// e respoectivamente ela precisa de outras 2 propriedaddes, joincoulum que
	// recebe um @joinColumn, que fala quem é o principal dessa relação
	// w o inverseJoinColumns que recebe a mesma anotação, mas fala quem é o
	// secundario dessa relação

	public boolean senhaIgualA (String senha) {
		return getSenha().equals(senha);
	}

	public boolean senhaDiferente(String senha) {
		return senhaIgualA(senha);
	}

}
