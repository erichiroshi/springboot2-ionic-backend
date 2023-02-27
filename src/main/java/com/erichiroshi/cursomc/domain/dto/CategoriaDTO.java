package com.erichiroshi.cursomc.domain.dto;

import java.io.Serializable;

import com.erichiroshi.cursomc.domain.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;

	public CategoriaDTO(Categoria obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
	}
}
