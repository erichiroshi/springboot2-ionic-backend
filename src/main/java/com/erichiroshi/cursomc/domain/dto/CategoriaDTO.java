package com.erichiroshi.cursomc.domain.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.erichiroshi.cursomc.domain.Categoria;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;

	public CategoriaDTO(Categoria obj) {
		this.setId(obj.getId());
		this.setNome(obj.getNome());
	}
}
