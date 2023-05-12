package com.msr.logfluxo.api.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.msr.logfluxo.api.model.OcorrenciaModel;
import com.msr.logfluxo.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaMapper {
	
	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}

}
