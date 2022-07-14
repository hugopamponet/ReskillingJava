package com.reskilling.projeto.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reskilling.projeto.modelo.PautaModelo;

public interface PautaRepositorio extends JpaRepository<PautaModelo, Long> {
	
	Optional<PautaModelo> findByNome(String nome);
}