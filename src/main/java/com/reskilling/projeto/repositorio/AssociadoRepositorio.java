package com.reskilling.projeto.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reskilling.projeto.modelo.AssociadoModelo;

public interface AssociadoRepositorio extends JpaRepository<AssociadoModelo, Long> {
	
	List<AssociadoModelo> findAll();
	
	Optional<AssociadoModelo> findByCpf(String cpf);
}