package com.msr.logfluxo.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.msr.logfluxo.domain.model.Entrega;
import com.msr.logfluxo.domain.model.StatusEntrega;
import com.msr.logfluxo.domain.repository.ClienteRepository;
import com.msr.logfluxo.domain.repository.EntregaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private ClienteRepository clienteRepositpry;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {

		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
