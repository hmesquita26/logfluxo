package com.msr.logfluxo.domain.service;

import org.springframework.stereotype.Service;

import com.msr.logfluxo.domain.exception.EntidadeNaoEncontradaException;
import com.msr.logfluxo.domain.model.Entrega;
import com.msr.logfluxo.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}
}
