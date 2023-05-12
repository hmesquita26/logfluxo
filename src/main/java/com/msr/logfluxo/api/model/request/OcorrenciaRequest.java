package com.msr.logfluxo.api.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OcorrenciaRequest {

	@NotBlank
	private String descricao;
}
