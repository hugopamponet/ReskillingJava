package com.reskilling.projeto.servico;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.reskilling.projeto.DTO.PautaDTO;
import com.reskilling.projeto.componentes.VotacaoComponetes;
import com.reskilling.projeto.modelo.PautaModelo;
import com.reskilling.projeto.repositorio.PautaRepositorio;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class PautaServico {
	@Autowired
	private PautaRepositorio pautaRepositorio;
	
	public ResponseEntity<Object> save(PautaDTO pautaDTO) {
		
		Optional<PautaModelo> pautaRequested = pautaRepositorio.findById(pautaDTO.getPautaId());
		if(pautaRequested.isPresent()) {
			return ResponseEntity.badRequest().body(
			  VotacaoComponetes.builder()
			  .status(HttpStatus.BAD_REQUEST)
			  .mensagem("Pauta já existe")
			  .objeto(pautaRequested.get())
			  .build()
			 
			);
		}
		PautaModelo pauta = new PautaModelo();
		
		  pauta.setNome(pautaDTO.getNome());
		  pauta.setDescricao(pautaDTO.getDescricao());
		  pauta.setPautaId(pautaDTO.getPautaId());
		  pauta.setVotou(pautaDTO.getVotou()); pauta.setHorario(LocalDateTime.now());
		  pauta = pautaRepositorio.save(pauta);
		 
		
		return ResponseEntity.created(URI.create("/pauta")).body(pauta);
	}
	//@Transactional
	public ResponseEntity<Object> findById(Long idsessao) {
		Optional<PautaModelo> pauta = pautaRepositorio.findById(idsessao);
		
		if(pauta.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(pauta.get());
	}
}