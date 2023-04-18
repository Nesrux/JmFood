package com.nesrux.jmfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nesrux.jmfood.domain.model.pedido.FormaPagamento;

public interface FormaPagamentoRepository  extends JpaRepository<FormaPagamento,Long>{
}
