package com.erichiroshi.cursomc.domain.dto;

import java.io.Serializable;

import com.erichiroshi.cursomc.domain.Categoria;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class CategoriaDTO implements Serializable {

	@Getter	@Setter	private Integer id;
	@Getter	@Setter	private String name;

	public CategoriaDTO(Categoria obj) {
		this.id = obj.getId();
		this.name = obj.getNome();
	}

}
