package com.nesrux.jmfood.api.exceptionHandler;

import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final String MSG_ERRO_DE_SINTASE_CORPO_REQUISICAO = "o corpo da requisição esta invalido. Verifique o erro de sintaxe.";
    private static final String MSG_PROPRIEDADE_INEXISTENTE = "A Propreidade %s não existe, por favor corrija o erro e tente novamente";
    private static final String MSG_PROPRIEDADE_COM_VALOR_ERRADO = "A propriedade '%s' recebeu o valor '%s que é um tipo inválido. Corrija e informe um valor compativel com o tipo %s";
    private static final String MSG_RECURSO_INEXISTENTE = "O recurso %s que você tentou acessar, é inexistente";
    private static final String MSG_PARAMETRO_URL_INVALIDO = "O parâmentro de URL '%s' recebeu o valor '%s', que é de um tipo inválido, corrija e informe um valor compatível com o tipo '%s'";
    private static final String MSG_ERRO_GENERICO = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contado com o adiministrador do sistema.";

    /*
     * Essa classe ResponseEntityExceptionHandler é uma classe utilitaria que trata
     * todas as possiveis exceptions do spring MVC como a classe
     * httpMediaTypeNotaceppt
     */

    /*
     * Essa anotação controllerAdvice "fala para o spring" que essa classe vai
     * capturar todas as execoes
     */

    /*
     * a anotação @ExceptionHandler captura as execoes mandaddas, ela é anotada em
     * cima dos métodos e pode ser utilizado para criar respostas de erros dinamicas
     * Ele só captura as exceptions geradas nesse controlador
     */
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(EntidadeNaoEncontradaException e, WebRequest request) {

	// return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(),
	// HttpStatus.BAD_REQUEST, request);

	HttpStatus status = HttpStatus.NOT_FOUND;
	TipoProblema tipoProblema = TipoProblema.ENTIDADE_EM_USO;
	String detail = e.getMessage();
	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();

	return handleExceptionInternal(e, erro, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException e, WebRequest request) {

	HttpStatus status = HttpStatus.NOT_FOUND;
	TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;
	String detail = e.getMessage();

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();
	/**
	 * Ambos códigos são o mesmo e funcionam da mesma forma, a diferença é que fica
	 * mais eleante e mais pratico para manutenção!
	 */

	// ErroApi erro = ErroApi.builder().status(status.value())
	// .type("https://jmfood.com.br/entidade-nao-encontrada")
	// .title("Entidade Não Encontrada").detail(detail).build();

	return handleExceptionInternal(e, erro, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> hendleEntidadeEmUsoException(EntidadeNaoEncontradaException e, WebRequest request) {
	HttpStatus status = HttpStatus.CONFLICT;
	TipoProblema tipoProblema = TipoProblema.ENTIDADE_EM_USO;
	String detail = e.getMessage();

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).userMessage(detail).build();

	return handleExceptionInternal(e, erro, null, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> hendleArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
	HttpStatus status = HttpStatus.BAD_REQUEST;
	TipoProblema tipoProblema = TipoProblema.PARAMETRO_INVALIDO;

	String detail = String.format(MSG_PARAMETRO_URL_INVALIDO, ex.getParameter().getParameterName(), ex.getValue(),
		ex.getRequiredType().getSimpleName());

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();

	return handleExceptionInternal(ex, erro, null, status, request);
    }

    // Trata todas as exceptions de forma global
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlAllExceptions(Exception ex, WebRequest request) {
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	TipoProblema tipoProblema = TipoProblema.ERRO_DE_SISTEMA;

	HttpHeaders headers = new HttpHeaders();

	String detail = String.format(MSG_ERRO_GENERICO);

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();

	return handleExceptionInternal(ex, erro, headers, status, request);
    }

    // ============== MÉTODOS SOBRESCRITOS DA CLASSE PAI =======================
    // Erro de sintax no corpo da requisição
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {

	Throwable causaRaiz = ExceptionUtils.getRootCause(ex);

	if (causaRaiz instanceof InvalidFormatException) {
	    return handleInvalidFormatException((InvalidFormatException) causaRaiz, headers, status, request);
	} else if (causaRaiz instanceof PropertyBindingException) {
	    return handlePropertyBindException(((PropertyBindingException) causaRaiz), headers, status, request);
	}

	TipoProblema tipoProblema = TipoProblema.MENSAGEM_INCOMPREENSIVEL;

	String detail = MSG_ERRO_DE_SINTASE_CORPO_REQUISICAO;

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).build();

	return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);

    }

    // Erro de parametro na URL jmfood/teste/
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	TipoProblema problema = TipoProblema.RECURSO_NAO_ENCONTRADO;
	String detail = String.format(MSG_RECURSO_INEXISTENTE, ex.getRequestURL());
	ErroApi erro = criacaoDeBilderProblema(status, problema, detail).build();

	return handleExceptionInternal(ex, erro, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	if (body == null) {
	    body = ErroApi.builder().title(status.getReasonPhrase()).status(status.value()).build();
	} else if (body instanceof String) {
	    body = ErroApi.builder().title((String) body).status(status.value()).build();
	}
	// Verifica se o cabeçalho esta nulo, se estiver ele atribui um
	// cabeçaçho padrao
	else if (headers == null) {
	    headers = new HttpHeaders();
	}
	return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    // ======================= MÉTODOS PRIVADOS ========================== \\

    /*
     * Ele retorna um erroAPIBuider uma classe criada pelo lombok que usa o padrão
     * Builder de projeto. como esse método não possui um .build no final do método,
     * ele vai retornar uma pseudo intancia de ErroAPI, que vai poder ser editado em
     * qualquer método que chame ele
     */

    private ErroApi.ErroApiBuilder criacaoDeBilderProblema(HttpStatus status, TipoProblema tipoProblema,
	    String detail) {
	return ErroApi.builder().status(status.value()).type(tipoProblema.getUri()).title(tipoProblema.getTitulo())
		.detail(detail);
    }

    // Erro de valores, precisava de um tipo Long e veio string.
    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	TipoProblema tipoProblema = TipoProblema.MENSAGEM_INCOMPREENSIVEL;

	// Faz um a junção dos nomes dos objetos com um "."
	String caminho = ex.getPath().stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));

	String detail = String.format(MSG_PROPRIEDADE_COM_VALOR_ERRADO, caminho, ex.getValue(),
		ex.getTargetType().getSimpleName());

	ErroApi erro = criacaoDeBilderProblema(status, tipoProblema, detail).userMessage(MSG_ERRO_GENERICO).build();

	return handleExceptionInternal(ex, erro, headers, status, request);
    }

    // Propriedade inxistente ou propriedade ignorada com @jsonignore
    private ResponseEntity<Object> handlePropertyBindException(PropertyBindingException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {
	TipoProblema problema = TipoProblema.ERRO_DE_NEGOCIO;
	String detail = String.format(MSG_PROPRIEDADE_INEXISTENTE, ex.getPropertyName());

	ErroApi erro = criacaoDeBilderProblema(status, problema, detail).userMessage(MSG_ERRO_GENERICO).build();

	return handleExceptionInternal(ex, erro, headers, status, request);
    }

}
