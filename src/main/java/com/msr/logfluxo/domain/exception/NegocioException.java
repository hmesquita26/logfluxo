package com.msr.logfluxo.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@SuppressWarnings("serial")
@AllArgsConstructor
@Getter
public class NegocioException extends RuntimeException{
	
	private String mensagem;

}
