package com.msr.logfluxo.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.msr.logfluxo.domain.exception.EntidadeNaoEncontradaException;
import com.msr.logfluxo.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<CausaException.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors() ) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new CausaException.Campo(nome, mensagem));
		}
			
		CausaException causa = new CausaException();
		causa.setStatus(status.value());
		causa.setDataHora(OffsetDateTime.now());
		causa.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.");
		causa.setCampos(campos);
		
		return handleExceptionInternal(ex, causa, headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleNegocio(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		CausaException causa = new CausaException();
		causa.setStatus(status.value());
		causa.setDataHora(OffsetDateTime.now());
		causa.setTitulo(ex.getMensagem());
		
		return handleExceptionInternal(ex, causa, new HttpHeaders(), status, request);	
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		CausaException causa = new CausaException();
		causa.setStatus(status.value());
		causa.setDataHora(OffsetDateTime.now());
		causa.setTitulo(ex.getMensagem());
		
		return handleExceptionInternal(ex, causa, new HttpHeaders(), status, request);	
	}
	
}
