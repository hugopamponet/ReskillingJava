package com.reskilling.projeto.componentes;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.reskilling.projeto.DTO.AssociadoDTO;
import com.reskilling.projeto.modelo.AssociadoModelo;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AssembleiaDeAssociadosComponentes {
	private static final ModelMapper modelMapper = new ModelMapper();
	
	public AssociadoDTO toDTO(AssociadoModelo associado) {
		return modelMapper.map(associado, AssociadoDTO.class);
	}
	public Page<AssociadoDTO> toPageDTO(Page<AssociadoModelo> associado){
		
		return associado.map(t -> toDTO(t));
	}
	public AssociadoModelo toEntity(AssociadoDTO associadoDTO) {
		return modelMapper.map(associadoDTO, AssociadoModelo.class);
	}
}