package com.msr.logfluxo.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.msr.logfluxo.api.mapper.OcorrenciaMapper;
import com.msr.logfluxo.api.model.OcorrenciaModel;
import com.msr.logfluxo.api.model.request.OcorrenciaRequest;
import com.msr.logfluxo.domain.model.Entrega;
import com.msr.logfluxo.domain.model.Ocorrencia;
import com.msr.logfluxo.domain.service.BuscaEntregaService;
import com.msr.logfluxo.domain.service.RegistroOcorrenciaService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {
	
	private BuscaEntregaService buscaEntregaService;
	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaMapper ocorrenciaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
		Ocorrencia ocorrenciaRegsitrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaRequest.getDescricao());
		
		return ocorrenciaMapper.toModel(ocorrenciaRegsitrada);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaMapper.toCollectionModel(entrega.getOcorrencias());
	}

}
