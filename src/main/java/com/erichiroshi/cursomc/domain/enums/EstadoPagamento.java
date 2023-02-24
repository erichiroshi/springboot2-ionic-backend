package com.erichiroshi.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private int code;
	private String descricao;

	private EstadoPagamento(int code, String descricao) {
		this.code = code;
		this.descricao = descricao;
	}

	public static EstadoPagamento valueOf(int code) {
		for (EstadoPagamento value : EstadoPagamento.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid EstadoPagamento code");
	}

}
