package com.erichiroshi.cursomc.resources.exceptions;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class FieldMessage implements Serializable {

	@Getter @Setter private String fieldName;
	@Getter @Setter private String message;

	public FieldMessage(String fieldName, String message) {
		this.fieldName = fieldName;
		this.message = message;
	}

}