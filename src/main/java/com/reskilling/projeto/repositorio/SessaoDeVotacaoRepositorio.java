package com.reskilling.projeto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.reskilling.projeto.modelo.SessaoDeVotacaoModelo;

public interface SessaoDeVotacaoRepositorio extends JpaRepository<SessaoDeVotacaoModelo, Long>{
	
	@Query("SELECT s FROM SessaoDeVotacaoModelo s WHERE s.active = true AND s.id = :sessaoId")
	 Optional<SessaoDeVotacaoModelo> findById(@Param("sessaoId") Long id);

	  @Modifying
	  @Query(value = "UPDATE SessaoDeVotacaoModelo s SET s.active = false WHERE s.id = ?1",
	      nativeQuery = false) 
	 void disableSession(Long pauta);
	  
	  //Nomenando a instrução.

}
