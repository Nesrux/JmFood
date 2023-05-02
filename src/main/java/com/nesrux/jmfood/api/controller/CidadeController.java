package com.nesrux.jmfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.EstadoNaoEncontradoException;
import com.nesrux.jmfood.domain.model.endereco.Cidade;
import com.nesrux.jmfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value ="/cidades")
public class CidadeController {

    @Autowired
    private CadastroCidadeService cidadeService;

    @GetMapping
    public List<Cidade> listar() {
	return cidadeService.acharTodas();
    }

    @GetMapping("{cidadeId}")
    @ResponseStatus(HttpStatus.OK)
    public Cidade buscar(@PathVariable Long cidadeId) {
	Cidade cidade = cidadeService.acharOuFalhar(cidadeId);
	return cidade;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Cidade adicionar(@RequestBody @Valid Cidade cidade) {
	try {
	    return cidadeService.salvar(cidade);
	} catch (EstadoNaoEncontradoException e) {
	    throw new NegocioException(e.getMessage(), e);
	}
    }

    @PutMapping("/{cidadeId}")
    public Cidade atualizar(@PathVariable Long cidadeId, @RequestBody @Valid Cidade cidade) {
	try {
	    Cidade cidadeAtual = cidadeService.acharOuFalhar(cidadeId);

	    BeanUtils.copyProperties(cidade, cidadeAtual, "id");

	    return cidadeService.salvar(cidadeAtual);
	} catch (EstadoNaoEncontradoException e) {
	    throw new NegocioException(e.getMessage(), e);
	}
    }

    @DeleteMapping("{cidadeId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long cidadeId) {
	cidadeService.excluir(cidadeId);
    }
}
