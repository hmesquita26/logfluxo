package com.msr.logfluxo.api.model.request;

import java.math.BigDecimal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaRequest {

	@Valid
	@NotNull
	private ClienteIdRequest cliente;

	@Valid
	@NotNull	
	private DestinatarioRequest destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
}
