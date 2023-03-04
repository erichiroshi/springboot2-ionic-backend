package com.erichiroshi.cursomc.resources.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.erichiroshi.cursomc.services.exceptions.AuthorizationException;
import com.erichiroshi.cursomc.services.exceptions.DataIntegrityException;
import com.erichiroshi.cursomc.services.exceptions.FileException;
import com.erichiroshi.cursomc.services.exceptions.ObjectNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Objeto não encontrado.";
		StandardError err = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Entidade com entidades associadas.";
		StandardError err = new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de validação.";
		ValidationError err = new ValidationError(LocalDateTime.now(), status.value(), error, "Campo preenchido de forma incorreta", request.getRequestURI());

		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		String error = "Acesso negado.";
		StandardError err = new StandardError(LocalDateTime.now(), HttpStatus.FORBIDDEN.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "S3 ERROR";
		StandardError err =  new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.valueOf(e.getErrorCode());
		String error = "S3 ERROR";
		StandardError err =  new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "S3 ERROR";
		StandardError err =  new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "S3 ERROR";
		StandardError err =  new StandardError(LocalDateTime.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);
	}

}
