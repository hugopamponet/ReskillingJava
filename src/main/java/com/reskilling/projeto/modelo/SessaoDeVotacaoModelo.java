package com.reskilling.projeto.modelo;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "sessaodevotacao")
public class SessaoDeVotacaoModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "ativo")
	private boolean active;
	
	@Column(name = "sessaoId")
	private Long sessaoId;
	
	@Column(name = "horario_inicial")
	private LocalDateTime horarioInicial;
	
	@Column(name = "horario_final")
	private LocalDateTime horarioFinal;
	
	@OneToOne
	@JoinColumn(name = "id")
	private PautaModelo pautaModelo;
}
