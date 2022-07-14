package com.reskilling.projeto.componentes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reskilling.projeto.repositorio.SessaoDeVotacaoRepositorio;


@Service
public class EncerrarSessaoComponentes {
	
	private static final Logger logger = LogManager.getLogger(EncerrarSessaoComponentes.class);
	@Autowired
	private SessaoDeVotacaoRepositorio sessaoDeVotacaoRepositorio;

	  @Async
	  @Transactional
	  public void disable(Long sessao, int timeOut) {
	    int umMinuto = 60 * 1000;
	    try {
	      Thread.sleep(umMinuto * timeOut);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    sessaoDeVotacaoRepositorio.disableSession(sessao);
	    logger.error("Tempo esgotado");
	  }
}