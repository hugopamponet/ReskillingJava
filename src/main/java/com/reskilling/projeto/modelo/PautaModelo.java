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
@Table(name = "Pauta")
public class PautaModelo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descrição")
	private String descricao;
	
	@Column(name = "pautaId")
	private Long pautaId;
	
	@Column(name = "votou")
	private String votou;
	
	@Column(name = "horario")
	private LocalDateTime horario;
}