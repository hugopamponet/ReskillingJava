package com.reskilling.projeto.componentes;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotacaoComponetes {
	
	private HttpStatus status;
	private String mensagem;
	private Object objeto;
}