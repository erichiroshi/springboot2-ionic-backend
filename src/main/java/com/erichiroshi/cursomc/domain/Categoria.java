package com.erichiroshi.cursomc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Categoria implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Integer id;
	@Getter @Setter private String nome;
	
	@ManyToMany(mappedBy = "categorias")
	@Getter Set<Produto> produtos = new HashSet<>();
	
	public Categoria(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
}
