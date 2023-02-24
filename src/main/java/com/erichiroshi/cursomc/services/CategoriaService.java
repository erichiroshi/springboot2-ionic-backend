package com.erichiroshi.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erichiroshi.cursomc.domain.Categoria;
import com.erichiroshi.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria findById(Integer id) {
		return repository.findById(id).orElseThrow();
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

}
