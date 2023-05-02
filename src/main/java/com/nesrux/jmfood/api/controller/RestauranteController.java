package com.nesrux.jmfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;
import com.nesrux.jmfood.domain.model.restaurante.Restaurante;
import com.nesrux.jmfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService restauranteService;

    @GetMapping()
    public List<Restaurante> listar() {
	return restauranteService.acharTodos();
    }

    @GetMapping("/{restauranteId}")
    public Restaurante buscar(@PathVariable Long restauranteId) {
	Restaurante restaurante = restauranteService.acharOuFalhar(restauranteId);

	return restaurante;
    }
    // O @valid vai validar o objeto enviado na requisição na hora que ele chega no
    // metodo adicionar, ao invés dele fazer isso na hora da persistencia de dados,
    // ou seja, ele nem chega a ir para a camada de dominio, facilitando a
    // manipulaçao das exceptions

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
	try {
	    return restaurante = restauranteService.salvar(restaurante);
	} catch (EntidadeNaoEncontradaException e) {
	    throw new NegocioException(e.getMessage(), e);
	}
    }

    @PutMapping("/{restauranteId}")
    public Restaurante atualizar(@PathVariable Long restauranteId, @RequestBody @Valid Restaurante restaurante) {
	try {
	    Restaurante restauranteAtual = restauranteService.acharOuFalhar(restauranteId);

	    BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
		    "dataCadastro");

	    return restauranteService.salvar(restauranteAtual);
	} catch (EntidadeNaoEncontradaException e) {
	    throw new NegocioException(e.getMessage(), e);
	}
    }

    @PatchMapping("{restauranteId}")
    public Restaurante atualizaParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos,
	    HttpServletRequest request) {
	Restaurante restauranteAtual = restauranteService.acharOuFalhar(restauranteId);

	merge(campos, restauranteAtual, request);

	return atualizar(restauranteId,  restauranteAtual);
    }

    private void merge(Map<String, Object> camposOrigem, Restaurante restaurantesDestino, HttpServletRequest request) {
	ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);

	try {
	    ObjectMapper mapper = new ObjectMapper();

	    // Configuração manual do mapper, pois esta sendo instanciado manualmente e não
	    // automaticamente.
	    mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

	    Restaurante restauranteOrigem = mapper.convertValue(camposOrigem, Restaurante.class);

	    camposOrigem.forEach((propriedade, valor) -> {

		Field field = ReflectionUtils.findField(Restaurante.class, propriedade);
		field.setAccessible(true);

		Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

		ReflectionUtils.setField(field, restaurantesDestino, novoValor);

	    });
	} catch (IllegalArgumentException e) {
	    Throwable causaRaiz = ExceptionUtils.getRootCause(e);
	    throw new HttpMessageNotReadableException(e.getMessage(), causaRaiz, httpRequest);
	}
    }
}
