package com.nesrux.jmfood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.Query;

import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;

public interface FormaPagamentoRepository  extends CustomJpaRepository<FormaPagamento,Long>{
	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataUltimaAtualizacao();

}
