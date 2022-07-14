package com.reskilling.projeto.servico;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.reskilling.projeto.DTO.AssociadoDTO;
import com.reskilling.projeto.componentes.AssembleiaDeAssociadosComponentes;
import com.reskilling.projeto.modelo.AssociadoModelo;
import com.reskilling.projeto.repositorio.AssociadoRepositorio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AssociadoServico {
	
	private AssociadoRepositorio associadoRepositorio;
	private AssembleiaDeAssociadosComponentes assembleiaDeAssociados;
	
	public Page<AssociadoDTO> findAll(Pageable pageable) {
		Page<AssociadoDTO> associadoDTO = assembleiaDeAssociados.toPageDTO(associadoRepositorio.findAll(pageable));
		return associadoDTO;
	}
	public ResponseEntity<String> save(AssociadoDTO associadoDTO) {
		if(cpfForInvalido(associadoDTO.getCpf())) {
			return ResponseEntity.badRequest().body("CPF invalido.");
		}
		if(associadoRepositorio.findByCpf(associadoDTO.getCpf()).isPresent()) {
			return ResponseEntity.badRequest().body("CPF existe");
		}
		AssociadoModelo associado = assembleiaDeAssociados.toEntity(associadoDTO);
		associado = associadoRepositorio.save(associado);
		return ResponseEntity.created(URI.create("/cadastrar")).body("Associado criado");
	}
	public boolean cpfForInvalido(String cpf) {
		return cpf.length() == 11;
	}
	public boolean cpfInvalido(String cpf) {
		return !this.cpfInvalido(cpf);
	}
}