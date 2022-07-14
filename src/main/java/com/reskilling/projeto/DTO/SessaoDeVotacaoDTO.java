package com.reskilling.projeto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDeVotacaoDTO {
	
	private Long id;
	private String nome;
	private Long sessaoId;
	private int tempo = 1;
	private int time;
}