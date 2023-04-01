package com.nesrux.jmfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.exception.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.Estado;
import com.nesrux.jmfood.domain.repository.EstadoRepository;
import com.nesrux.jmfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService estadoService;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);

		if (estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
		estado = estadoService.salvar(estado);
		return ResponseEntity.status(HttpStatus.CREATED).body(estado);
	}

	@PutMapping("{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);

		if (estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");

			Estado estadoSalvo = estadoService.salvar(estadoAtual.get());

			return ResponseEntity.ok(estadoSalvo);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("{estadoId}")
	public ResponseEntity<?> deletar(@PathVariable Long estadoId) {
		try {
			estadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
