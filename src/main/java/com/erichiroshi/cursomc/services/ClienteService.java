package com.erichiroshi.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erichiroshi.cursomc.domain.Cliente;
import com.erichiroshi.cursomc.repositories.ClienteRepository;
import com.erichiroshi.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
											"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

}
