package com.erichiroshi.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.erichiroshi.cursomc.domain.enums.TipoCliente;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cliente implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Integer id;
	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String cpfOuCnpj;
	private Integer tipo;
	
	@OneToMany(mappedBy = "cliente")
	@Getter private Set<Endereco> enderecos = new HashSet<>();
	
	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	@Getter private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "cliente")
	@Getter private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo.getCod();
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}
}
