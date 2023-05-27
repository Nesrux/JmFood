package com.nesrux.jmfood.infrastructure.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.nesrux.jmfood.domain.filter.VendaDiariaFilter;
import com.nesrux.jmfood.domain.model.dto.VendaDiaria;
import com.nesrux.jmfood.domain.service.VendaQueryService;

public class VendaQueryServiceImpl implements VendaQueryService {
	@Autowired
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendaDiarias(VendaDiariaFilter filtro) {
		return null;
	}

}
