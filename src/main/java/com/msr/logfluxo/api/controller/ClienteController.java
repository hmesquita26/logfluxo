package com.msr.logfluxo.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msr.logfluxo.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Felipe");
		cliente1.setTelefone("99 99999-9999");
		cliente1.setEmail("teste@teste.com.br");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Peralta");
		cliente2.setTelefone("88 98888-9999");
		cliente2.setEmail("teste@teste.com.br");		
		
		return Arrays.asList(cliente1, cliente2);
	}

}
