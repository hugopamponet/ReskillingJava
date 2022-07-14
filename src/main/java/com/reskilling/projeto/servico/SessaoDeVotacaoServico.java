package com.reskilling.projeto.servico;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reskilling.projeto.DTO.SessaoDeVotacaoDTO;
//import com.reskilling.projeto.componentes.EncerrarSessaoComponentes;
import com.reskilling.projeto.componentes.VotacaoComponetes;
import com.reskilling.projeto.modelo.PautaModelo;
import com.reskilling.projeto.modelo.SessaoDeVotacaoModelo;
import com.reskilling.projeto.repositorio.PautaRepositorio;
import com.reskilling.projeto.repositorio.SessaoDeVotacaoRepositorio;


@Service

public class SessaoDeVotacaoServico {
	@Autowired
	private SessaoDeVotacaoRepositorio sessaoDeVotacaoRepositorio;
	@Autowired
	private PautaRepositorio pautaRepositorio;
	//@Autowired
	//private EncerrarSessaoComponentes encerrarSessaoComponentes;
	
	@Transactional
	public ResponseEntity<Object> iniciarSessao(SessaoDeVotacaoDTO sessaoDeVotacaoDTO){
		if(sessaoDeVotacaoDTO.getNome().isBlank() || sessaoDeVotacaoDTO.getNome().isEmpty()) {
			return ResponseEntity.badRequest().body(
					VotacaoComponetes.builder()
					.status(HttpStatus.BAD_REQUEST)
					.mensagem("Titulo obrigatorio")
					.objeto(sessaoDeVotacaoDTO)
					.build()
			);
		}
		if(sessaoDeVotacaoDTO.getId() == null) {
			return ResponseEntity.badRequest().body(
					VotacaoComponetes.builder()
					.status(HttpStatus.BAD_REQUEST)
					.mensagem("Identificação da pauta obrigatorio")
					.objeto(sessaoDeVotacaoDTO)
					.build()
			);
		}
		Optional<PautaModelo> pauta = pautaRepositorio.findById(sessaoDeVotacaoDTO.getId());
		if(pauta.isEmpty()) {
			return ResponseEntity.badRequest().body(
					VotacaoComponetes.builder()
					.status(HttpStatus.BAD_REQUEST)
					.mensagem("Pauta não encontrada")
					.objeto(sessaoDeVotacaoDTO)
					.build()
			);
		}
		Optional<SessaoDeVotacaoModelo> sessaoOndb = sessaoDeVotacaoRepositorio.findById(sessaoDeVotacaoDTO.getId());
		if(sessaoOndb.isPresent()) {
			return ResponseEntity.badRequest().body(
					VotacaoComponetes.builder()
					.status(HttpStatus.BAD_REQUEST)
					.mensagem("Sessão já aberta")
					.objeto(sessaoDeVotacaoDTO)
					.build()
			);
		}
		SessaoDeVotacaoModelo sessaoDeVotacao = new SessaoDeVotacaoModelo();
		sessaoDeVotacao.setNome(sessaoDeVotacaoDTO.getNome());
		sessaoDeVotacao.setActive(true);
		sessaoDeVotacao.setSessaoId(sessaoDeVotacaoDTO.getSessaoId());
		LocalDateTime now = LocalDateTime.now();
		sessaoDeVotacao.setHorarioInicial(now);
		
		int incrementoMinutos = sessaoDeVotacaoDTO.getTime() > 0 ?
				sessaoDeVotacaoDTO.getTime() :
				sessaoDeVotacaoDTO.getTempo();
		sessaoDeVotacao.setHorarioInicial(now.plusMinutes(incrementoMinutos));
		sessaoDeVotacao.setPautaModelo(pauta.get());
		
		sessaoDeVotacao = sessaoDeVotacaoRepositorio.save(sessaoDeVotacao);
		
		//encerrarSessaoComponentes.disable(sessaoDeVotacao.getId(), incrementoMinutos);
		
		return ResponseEntity.created(URI.create("/iniciar/sessao")).body("Sessão aberta");
	}
}