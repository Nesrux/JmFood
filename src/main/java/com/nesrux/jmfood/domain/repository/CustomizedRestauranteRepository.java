package com.nesrux.jmfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.nesrux.jmfood.domain.model.Restaurante;

public interface CustomizedRestauranteRepository {

	List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal);

	List<Restaurante> findComFreteGratis(String nome);
}