package com.erichiroshi.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.erichiroshi.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	@JsonFormat(pattern = "dd/MM/yyyy")
	@Getter @Setter private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Getter @Setter private Date dataPagamento;

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

}
