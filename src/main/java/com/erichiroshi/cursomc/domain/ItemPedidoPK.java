package com.erichiroshi.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Embeddable
public class ItemPedidoPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "pedido_id")
	@Getter @Setter private Pedido pedido;

	@ManyToOne
	@JoinColumn(name = "produto_id")
	@Getter @Setter private Produto produto;
}
