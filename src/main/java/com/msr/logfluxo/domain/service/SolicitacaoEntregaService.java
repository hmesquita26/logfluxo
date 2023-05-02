package com.msr.logfluxo.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.msr.logfluxo.domain.model.Cliente;
import com.msr.logfluxo.domain.model.Entrega;
import com.msr.logfluxo.domain.model.StatusEntrega;
import com.msr.logfluxo.domain.repository.EntregaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private CatalogoClienteService catalogoClienteService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}
}
