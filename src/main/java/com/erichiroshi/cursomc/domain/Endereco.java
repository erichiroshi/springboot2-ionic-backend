package com.erichiroshi.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Endereco implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Integer id;
	@Getter @Setter private String logradouro;
	@Getter @Setter private String numero;
	@Getter @Setter private String complemento;
	@Getter @Setter private String bairro;
	@Getter @Setter private String cep;
	
	@ManyToOne
	@JoinColumn(name = "cidade_id")
	@Getter @Setter private Cidade cidade;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@Getter private Cliente cliente;

	public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep,
			Cidade cidade, Cliente cliente) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cliente = cliente;
	}
	
	
}
