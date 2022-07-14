package com.reskilling.projeto.controle;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reskilling.projeto.DTO.VotoDTO;
import com.reskilling.projeto.servico.VotoServico;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "voto")
@AllArgsConstructor
public class VotoControle {
	private VotoServico votoServico;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> votosdaSessaoId(@PathVariable Long id) {
		return votoServico.votosdaSessaoId(id);
	}
	@PostMapping(value = "/votacao")
	public ResponseEntity<Object> save(@RequestBody VotoDTO votoDTO) {
		
		return votoServico.save(votoDTO);
	}
}