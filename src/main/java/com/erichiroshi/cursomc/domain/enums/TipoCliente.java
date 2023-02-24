package com.erichiroshi.cursomc.domain.enums;

import lombok.Getter;

@Getter
public enum TipoCliente {

	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public static TipoCliente valueOf(int code) {
		for (TipoCliente value : TipoCliente.values()) {
			if (value.getCod() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid TipoCliente code");
	}

}
