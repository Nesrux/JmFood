package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.model.endereco.Estado;
import com.nesrux.jmfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private CadastroEstadoService estadoService;

    @GetMapping
    public List<Estado> listar() {
	return estadoService.acharTodos();
    }

    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId) {
	return estadoService.acharOuFalhar(estadoId);
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody @Valid Estado estado) {
	estado = estadoService.salvar(estado);
	return ResponseEntity.status(HttpStatus.CREATED).body(estado);
    }

    @PutMapping("{estadoId}")
    public Estado atualizar(@PathVariable Long estadoId, @RequestBody @Valid Estado estado) {
	Estado estadoAtual = estadoService.acharOuFalhar(estadoId);

	BeanUtils.copyProperties(estado, estadoAtual, "id");

	Estado estadoSalvo = estadoService.salvar(estadoAtual);

	return estadoSalvo;

    }

    @DeleteMapping("{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long estadoId) {
	estadoService.excluir(estadoId);
    }

}
