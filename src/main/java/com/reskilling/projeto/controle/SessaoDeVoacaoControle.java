package com.reskilling.projeto.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reskilling.projeto.DTO.SessaoDeVotacaoDTO;
import com.reskilling.projeto.servico.SessaoDeVotacaoServico;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("sessao")
@AllArgsConstructor
public class SessaoDeVoacaoControle {
	@Autowired
	private SessaoDeVotacaoServico sessaoDeVotacaoServico;
	
	@PostMapping(value = "/iniciarSessao")
	public ResponseEntity<Object> iniciarSessao(@RequestBody SessaoDeVotacaoDTO sessaoDeVotacaoDTO){
		return sessaoDeVotacaoServico.iniciarSessao(sessaoDeVotacaoDTO);
	}
}
