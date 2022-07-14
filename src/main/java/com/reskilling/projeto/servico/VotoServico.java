package com.reskilling.projeto.servico;

import java.net.URI;
import java.time.LocalDateTime;
//import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reskilling.projeto.DTO.ContagemDeVotosDTO;
import com.reskilling.projeto.DTO.VotoDTO;
import com.reskilling.projeto.componentes.VotacaoComponetes;
import com.reskilling.projeto.modelo.SessaoDeVotacaoModelo;
import com.reskilling.projeto.modelo.VotoModelo;
import com.reskilling.projeto.repositorio.SessaoDeVotacaoRepositorio;
import com.reskilling.projeto.repositorio.VotoRepositorio;

@Service

public class VotoServico {
	@Autowired
	private SessaoDeVotacaoRepositorio sessaoDeVotacaoRepositorio;
	@Autowired
	private VotoRepositorio votoRepositorio;
	
	@Transactional(readOnly = false)
	public ResponseEntity<Object> save(VotoDTO votoDTO) {
		Optional<SessaoDeVotacaoModelo> sessaoDeVotacaoModelo = sessaoDeVotacaoRepositorio.findById(votoDTO.getSessaoId());
		if(sessaoDeVotacaoModelo.isEmpty() || !sessaoDeVotacaoModelo.get().isActive()) {
			return ResponseEntity.badRequest().body(
			  VotacaoComponetes.builder()
			  .status(HttpStatus.BAD_REQUEST)
			  .mensagem("Sessão não encontrada")
			  .objeto(sessaoDeVotacaoModelo.orElse(null))
			  .build()
			);
		}
		Optional<VotoModelo> votoExistente = votoRepositorio.findBySessaoIdAndAssociadoId(votoDTO.getSessaoId(), votoDTO.getAssociadoId());
		VotoModelo newVotoModelo = new VotoModelo();
		
		  if(votoExistente.isPresent()) {
		  newVotoModelo = votoExistente.get();
		  newVotoModelo.setVoto(votoDTO.getVoto().toLowerCase());
		  newVotoModelo.setHorarioVotacao(LocalDateTime.now()); newVotoModelo =
		  votoRepositorio.saveAndFlush(newVotoModelo); 
		  return ResponseEntity.ok(newVotoModelo);
		  }
		  
		  newVotoModelo.setSessaoId(votoDTO.getSessaoId());
		  newVotoModelo.setPautaId(votoDTO.getPautaId());
		  newVotoModelo.setAssociadoId(votoDTO.getAssociadoId());
		  newVotoModelo.setVoto(votoDTO.getVoto());
		  newVotoModelo.setHorarioVotacao(LocalDateTime.now()); newVotoModelo =
		  votoRepositorio.save(newVotoModelo);
		 
		
		return ResponseEntity.created(URI.create("/votacao")).body(newVotoModelo);
	}
	
	@Transactional(readOnly = true)
	public ResponseEntity<Object> votosdaSessaoId(Long sessaoId){
		
		Optional<SessaoDeVotacaoModelo> sessaoDeVotacaoModelo = sessaoDeVotacaoRepositorio.findById(sessaoId);
		if(sessaoDeVotacaoModelo.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<VotoModelo> votoModelo = votoRepositorio.findBySessaoId(sessaoId);
		double totalDeVotos = votoModelo.size();
		double simVotos = votoModelo.stream()
			.filter(v -> v.getVoto().equals("Sim"))
			.count();
		double naoVotos = totalDeVotos - simVotos;
		return ResponseEntity.ok(
				ContagemDeVotosDTO.builder()
				.sessaoId(votoModelo.get(0).getSessaoId())
				.votosSim(simVotos)
				.votosNao(naoVotos)
				.pautaVencedora(simVotos > naoVotos)
				.pautaModelo(sessaoDeVotacaoModelo.get().getPautaModelo())
				.build()
				);
	}
}