package com.reskilling.projeto.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "voto")
public class VotoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "voto")
	private String voto;
	
	@Column(name = "sessaoId")
	private Long sessaoId;
	
	@Column(name = "pautaId")
	private Long pautaId;
	
	@Column(name = "associadoId")
	private Long associadoId;
	
	@Column(name = "horario_de_votacao")
	private LocalDateTime horarioVotacao;
}