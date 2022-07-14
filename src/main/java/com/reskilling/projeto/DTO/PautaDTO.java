package com.reskilling.projeto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaDTO {
	
	private String nome;
	private String descricao;
	private Long pautaId;
	private String votou;
}