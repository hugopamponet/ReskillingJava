package com.reskilling.projeto.repositorio;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.reskilling.projeto.modelo.VotoModelo;

public interface VotoRepositorio extends JpaRepository<VotoModelo, Long>{
	
	@Query("UPDATE VotoModelo v SET v.voto =?1, v.horarioVotacao =?2 WHERE v.sessaoId = ?3 AND v.associadoId = ?4")
	Collection<VotoModelo> saveVotoModelo(String voto, LocalDateTime horarioVotacao, Long sessaoId, Long pautaId,Long associadoId);

	 Optional<VotoModelo> findBySessaoIdAndAssociadoId(Long sessaoId, Long associadoId);

	 List<VotoModelo> findBySessaoId(Long sessaoId);
}
