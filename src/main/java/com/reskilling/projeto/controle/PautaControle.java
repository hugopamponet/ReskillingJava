package com.reskilling.projeto.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reskilling.projeto.DTO.PautaDTO;
import com.reskilling.projeto.servico.PautaServico;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "pauta")
public class PautaControle {
	@Autowired
	private PautaServico pautaServico;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<Object> save(@RequestBody PautaDTO pautaDTO) {
		return pautaServico.save(pautaDTO);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		return pautaServico.findById(id);
	}
}
