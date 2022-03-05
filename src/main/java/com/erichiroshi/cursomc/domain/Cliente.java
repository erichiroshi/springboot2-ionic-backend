package com.erichiroshi.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.erichiroshi.cursomc.domain.enums.Perfil;
import com.erichiroshi.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Integer id;
	@Getter @Setter private String nome;
	
	@Column(unique = true)
	@Getter @Setter private String email;
	@Getter @Setter private String cpfOuCnpj;
	private Integer tipo;
	
	@JsonIgnore
	@Getter @Setter private String senha;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	@Getter private Set<Endereco> enderecos = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Getter private Set<String> telefones = new HashSet<>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	@Getter private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {
		addPerfil(Perfil.CLIENTE);
	}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo, String senha) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = (tipo == null) ? null : tipo.getCod();
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		perfis.add(perfil.getCod());
	}

}
