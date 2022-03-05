package com.erichiroshi.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError implements Serializable {

	public StandardError(Integer status, String msg, Instant timestamp) {
		this.timestamp = timestamp;
		this.status = status;
		this.msg = msg;
	}
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	private String msg;
	private String path;

}
