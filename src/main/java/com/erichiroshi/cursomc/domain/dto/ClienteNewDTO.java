package com.erichiroshi.cursomc.domain.dto;

import java.io.Serializable;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
public class ClienteNewDTO  implements Serializable {

	@Getter @Setter private String nome;
	@Getter @Setter private String email;
	@Getter @Setter private String cpfOuCnpj;
	@Getter @Setter private Integer tipo;

	@Getter @Setter private String logradouro;
	@Getter @Setter private String numero;
	@Getter @Setter private String complemento;
	@Getter @Setter private String bairro;
	@Getter @Setter private String cep;

	@Getter @Setter private String telefone1;
	@Getter @Setter private String telefone2;
	@Getter @Setter private String telefone3;

	@Getter @Setter private Integer cidadeId;

}