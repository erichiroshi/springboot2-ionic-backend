package com.erichiroshi.cursomc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Getter @Setter private Integer id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@Getter @Setter private Date instant;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy="pedido")
	@Getter @Setter private Pagamento pagamento;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	@Getter @Setter private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "endereco_id")
	@Getter @Setter private Endereco enderecoDeEntrega;
	
	@OneToMany(mappedBy = "id.pedido")
	@Getter private Set<ItemPedido> itens = new HashSet<>();

	public Pedido(Integer id, Date instant, Cliente cliente, Endereco enderecoDeEntrega) {
		this.id = id;
		this.instant = instant;
		this.cliente = cliente;
		this.enderecoDeEntrega = enderecoDeEntrega;
	}
	
}
