package com.erichiroshi.cursomc.domain.dto;

import java.io.Serializable;

import com.erichiroshi.cursomc.domain.Estado;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EstadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public EstadoDTO(Estado obj) {
		id = obj.getId();
		nome = obj.getNome();
	}
}
