package com.reskilling.projeto.DTO;

import com.reskilling.projeto.modelo.PautaModelo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ContagemDeVotosDTO {
	
	private long sessaoId;
	private double votosSim;
	private double votosNao;
	private boolean pautaVencedora;
	private PautaModelo pautaModelo;
}