package com.erichiroshi.cursomc.services;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.erichiroshi.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, LocalDate instanteDoPedido) {
		pagto.setDataVencimento(instanteDoPedido.plusDays(7));
	}
}
