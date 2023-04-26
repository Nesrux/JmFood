package com.nesrux.jmfood.api.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler {
	/*
	 * Essa anotação controllerAdvice "fala para o spring" que essa classe vai
	 * capturat todas as execoes
	 */

	/*
	 * a anotação @ExceptionHandler captura as execoes mandaddas, ela é anotada em
	 * cima dos métodos e pode ser utilizado para criar respostas de erros dinamicas
	 * Ele só captura as exceptions geradas nesse controlador
	 */

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontrada(EntidadeNaoEncontradaException e) {
		ErroApi erro = ErroApi.builder()
				.datahora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> tratarNegocioException(EntidadeNaoEncontradaException e) {
		ErroApi erro = ErroApi.builder()
				.datahora(LocalDateTime.now())
				.mensagem(e.getMessage())
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> tratarErooMidiaType(){
		ErroApi erro = ErroApi.builder()
				.datahora(LocalDateTime.now())
				.mensagem("Tipo de midia não suportado")
				.build();

		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(erro);
	}
}
