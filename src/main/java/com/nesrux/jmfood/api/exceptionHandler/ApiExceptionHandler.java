package com.nesrux.jmfood.api.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	/*
	 * Essa classe ResponseEntityExceptionHandler é uma classe utilitaria que
	 * trata todas as possiveis exceptions do spring MVC como a classe
	 * httpMediaTypeNotaceppt
	 */

	/*
	 * Essa anotação controllerAdvice "fala para o spring" que essa classe vai
	 * capturat todas as execoes
	 */

	/*
	 * a anotação @ExceptionHandler captura as execoes mandaddas, ela é anotada
	 * em cima dos métodos e pode ser utilizado para criar respostas de erros
	 * dinamicas Ele só captura as exceptions geradas nesse controlador
	 */
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(
			EntidadeNaoEncontradaException e, WebRequest request) {

		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(),
				HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(
			EntidadeNaoEncontradaException e, WebRequest request) {

		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoProblema tipoProblema = TipoProblema.ENTIDADE_NAO_ENCONTRADA;
		String detail = e.getMessage();

		
		
		ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();
		
//		ErroApi erro = ErroApi.builder().status(status.value())
//				.type("https://jmfood.com.br/entidade-nao-encontrada")
//				.title("Entidade Não Encontrada").detail(detail).build();

		return handleExceptionInternal(e, erro, new HttpHeaders(),
				status, request);
	}

	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> hendleEntidadeEmUsoException(
			EntidadeNaoEncontradaException e, WebRequest request) {

		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(),
				HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
			Object body, HttpHeaders headers, HttpStatus status,
			WebRequest request) {

		if (body == null) {
			body = ErroApi.builder().title(status.getReasonPhrase())
					.status(status.value()).build();
		} else if (body instanceof String) {
			body = ErroApi.builder().title((String) body).status(status.value())
					.build();

		}
		return super.handleExceptionInternal(ex, body, headers, status,
				request);
	}
	
	private ErroApi.ErroApiBuilder criacaoDeBilderProblema(HttpStatus status, 
			TipoProblema tipoProblema, String detail) {
		return ErroApi.builder()
				.status(status.value())
				.type(tipoProblema.getUri())
				.title(tipoProblema.getTitulo())
				.detail(detail);
	}
}
