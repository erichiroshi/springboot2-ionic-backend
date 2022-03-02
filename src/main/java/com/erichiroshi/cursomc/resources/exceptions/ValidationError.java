package com.erichiroshi.cursomc.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class ValidationError extends StandardError {

	@Getter	private List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String msg, String path) {
		super(timestamp, status, error, msg, path);
	}

	public void addError(String fieldName, String messagem) {
		errors.add(new FieldMessage(fieldName, messagem));
	}
}