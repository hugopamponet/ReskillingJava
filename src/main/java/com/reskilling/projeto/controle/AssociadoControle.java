package com.reskilling.projeto.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reskilling.projeto.DTO.AssociadoDTO;
import com.reskilling.projeto.servico.AssociadoServico;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "associados")
public class AssociadoControle {
	@Autowired
	private AssociadoServico associadoServico;
	@PostMapping(value = "/salvar")
	public ResponseEntity<String> save(@RequestBody AssociadoDTO associadoDTO) {
		return associadoServico.save(associadoDTO);
	}
	@GetMapping(value = "/lista")
	public ResponseEntity<Page<AssociadoDTO>> findAll(Pageable pageable) {
		return ResponseEntity.ok(associadoServico.findAll(pageable));
	}
}
