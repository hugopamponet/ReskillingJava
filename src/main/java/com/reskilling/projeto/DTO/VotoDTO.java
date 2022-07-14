package com.reskilling.projeto.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoDTO {
	
	private String voto;
	private Long sessaoId;
	private Long pautaId;
	private Long associadoId;
}