package com.nesrux.jmfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.model.Estado;
import com.nesrux.jmfood.domain.repository.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.listar();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Estado estado = estadoRepository.buscar(estadoId);

		if (estado != null) {
			return ResponseEntity.ok(estado);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
