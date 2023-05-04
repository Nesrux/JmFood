package com.nesrux.jmfood;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.entidadeNaoEncontrada.CozinhaNaoEncontradaException;
import com.nesrux.jmfood.domain.model.restaurante.Cozinha;
import com.nesrux.jmfood.domain.service.CadastroCozinhaService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CadastroCozinhaIntegrationTeste {
//RunWith vai rodar o código junto com o spring, 
//Teste de intregração e teste DE api
    @Autowired
    private CadastroCozinhaService cozinhaService;

    @Test
    public void deveFuncionar_quandoCadastrarCozinhaValida() {
	// Caminho feliz, é o caminho onde todos os dados são validos

	// ele funciona em 3 passos, o cenario onde você estancia os objetos
	// e da um caminho para seguir.

	// A ação, onde de fato o código faz algo no sistema.

	// E a validação, onde o verifica se deu erro ou não.

	// Cenário
	Cozinha cozinha = new Cozinha();
	cozinha.setNome("Italo-americana");

	// Ação
	cozinha = cozinhaService.salvar(cozinha);

	// Validação
	assertThat(cozinha).isNotNull();
	assertThat(cozinha.getId()).isNotNull();
    }

    @Test(expected = ConstraintViolationException.class)
    public void deveFalhar_quandoCadastrarCozinhaSemNome() {
	// Caminho infeliz, onde acontece algum erro
	Cozinha cozinha = new Cozinha();
	cozinha.setNome(null);
	//
	cozinha = cozinhaService.salvar(cozinha);

    }

    @Test(expected = EntidadeEmUsoException.class)
    public void deveFalhar_quandoExcluirEmUso() {
	Cozinha cozinha = new Cozinha();
	cozinha = cozinhaService.buscaOuFalha(1L);
	//
	cozinhaService.excluir(cozinha.getId());
    }

    @Test(expected = CozinhaNaoEncontradaException.class)
    public void deveFalhar_quandoEcluirCozinhaInesistente() {
	cozinhaService.excluir(12L);
    }
}
