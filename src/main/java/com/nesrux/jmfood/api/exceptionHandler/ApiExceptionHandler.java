package com.nesrux.jmfood.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.nesrux.jmfood.domain.exception.NegocioException;
import com.nesrux.jmfood.domain.exception.ValidacaoException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeEmUsoException;
import com.nesrux.jmfood.domain.exception.negocioException.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
//ControllerAdvice é a anotação da classe para "avisar que essa classe" é expecializada em tratar exceptions
    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
	    + "o problema persistir, entre em contato com o administrador do sistema.";
    @Autowired
    private MessageSource messageSource;

    // Trata todas as exceptions de forma global
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	TipoProblema problemType = TipoProblema.ERRO_DE_SISTEMA;
	String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

	ex.printStackTrace();

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

	return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request) {

	HttpStatus status = HttpStatus.NOT_FOUND;

	TipoProblema problemType = TipoProblema.RECURSO_NAO_ENCONTRADO;
	String detail = ex.getMessage();

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

	return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(EntidadeEmUsoException ex, WebRequest request) {

	HttpStatus status = HttpStatus.CONFLICT;
	TipoProblema problemType = TipoProblema.ENTIDADE_EM_USO;
	String detail = ex.getMessage();

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

	return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocio(NegocioException ex, WebRequest request) {

	HttpStatus status = HttpStatus.BAD_REQUEST;
	TipoProblema problemType = TipoProblema.ERRO_DE_NEGOCIO;
	String detail = ex.getMessage();

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

	return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    // TODO Refatorar esse codigo futuramente para usar o mesmo codigo de
    // handleMethodArgumentNotValid
    @ExceptionHandler({ ValidacaoException.class })
    public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request) {
	String detail = String
		.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
	TipoProblema problema = TipoProblema.DADOS_INVALIDOS;
	HttpStatus status = HttpStatus.BAD_REQUEST;
	BindingResult bindingResult = ex.getBindingResult();
	List<ErroApi.Object> camposProblema = bindingResult.getAllErrors().stream().map(ObjectError -> {
	    String message = messageSource.getMessage(ObjectError, LocaleContextHolder.getLocale());
	    String name = ObjectError.getObjectName();
	    if (ObjectError instanceof FieldError) {
		name = ((FieldError) ObjectError).getField();
	    }

	    return ErroApi.Object.builder().nome(name).userMessage(message).build();
	}).collect(Collectors.toList());

	ErroApi erro = createProblemBuilder(status, problema, detail).userMessage(detail).objects(camposProblema)
		.build();

	return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	TipoProblema problemType = TipoProblema.RECURSO_NAO_ENCONTRADO;
	String detail = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
		.build();

	return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	if (ex instanceof MethodArgumentTypeMismatchException) {
	    return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
	}

	return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {
	Throwable rootCause = ExceptionUtils.getRootCause(ex);

	if (rootCause instanceof InvalidFormatException) {
	    return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
	} else if (rootCause instanceof PropertyBindingException) {
	    return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request);
	}

	TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
	String detail = "O corpo da requisição está inválido. Verifique erro de sintaxe.";

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
		.build();

	return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {

	String detail = String
		.format("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
	TipoProblema problema = TipoProblema.DADOS_INVALIDOS;

	BindingResult bindingResult = ex.getBindingResult();
	List<ErroApi.Object> camposProblema = bindingResult.getAllErrors().stream().map(ObjectError -> {
	    String message = messageSource.getMessage(ObjectError, LocaleContextHolder.getLocale());
	    String name = ObjectError.getObjectName();
	    if (ObjectError instanceof FieldError) {
		name = ((FieldError) ObjectError).getField();
	    }

	    return ErroApi.Object.builder().nome(name).userMessage(message).build();
	}).collect(Collectors.toList());

	ErroApi erro = createProblemBuilder(status, problema, detail).userMessage(detail).objects(camposProblema)
		.build();

	return handleExceptionInternal(ex, erro, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	if (body == null) {
	    body = ErroApi.builder().timesStamp(LocalDateTime.now()).title(status.getReasonPhrase())
		    .status(status.value()).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
	} else if (body instanceof String) {
	    body = ErroApi.builder().timesStamp(LocalDateTime.now()).title((String) body).status(status.value())
		    .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
	}

	return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	String path = joinPath(ex.getPath());

	TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
	String detail = String.format(
		"A propriedade '%s' não existe.  Corrija ou remova essa propriedade e tente novamente.", path);

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
		.build();

	return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ErroApi.ErroApiBuilder createProblemBuilder(HttpStatus status, TipoProblema problemType, String detail) {

	return ErroApi.builder().timesStamp(LocalDateTime.now()).status(status.value()).type(problemType.getUri())
		.title(problemType.getTitulo()).detail(detail);
    }

    private String joinPath(List<Reference> references) {
	return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex, HttpHeaders headers,
	    HttpStatus status, WebRequest request) {

	String path = joinPath(ex.getPath());

	TipoProblema problemType = TipoProblema.MENSAGEM_INCOMPREENSIVEL;
	String detail = String.format(
		"A propriedade '%s' recebeu o valor '%s', "
			+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
		path, ex.getValue(), ex.getTargetType().getSimpleName());

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
		.build();

	return handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
	    HttpHeaders headers, HttpStatus status, WebRequest request) {

	TipoProblema problemType = TipoProblema.PARAMETRO_INVALIDO;

	String detail = String.format(
		"O parâmetro de URL '%s' recebeu o valor '%s', "
			+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
		ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

	ErroApi problem = createProblemBuilder(status, problemType, detail).userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
		.build();

	return handleExceptionInternal(ex, problem, headers, status, request);
    }

}
