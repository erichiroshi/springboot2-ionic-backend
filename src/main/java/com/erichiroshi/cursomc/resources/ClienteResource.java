package com.erichiroshi.cursomc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.erichiroshi.cursomc.domain.Cliente;
import com.erichiroshi.cursomc.domain.dto.ClienteDTO;
import com.erichiroshi.cursomc.domain.dto.ClienteNewDTO;
import com.erichiroshi.cursomc.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = service.findById(id);
		return ResponseEntity.ok(cliente);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		List<ClienteDTO> listDto = list.stream().map(x -> new ClienteDTO(x)).toList();
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(value="/email", method=RequestMethod.GET)
	public ResponseEntity<Cliente> findByEmail(@RequestParam(value="value") String email) {
		Cliente obj = service.findByEmail(email);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> inserir(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Integer id, @Valid @RequestBody ClienteDTO objDto) {
		objDto.setId(id);
		Cliente obj = service.fromDTO(objDto);
		obj = service.update(obj);
		return ResponseEntity.ok(new ClienteDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(x -> new ClienteDTO(x));
		return ResponseEntity.ok(listDto);
	}
	
	@RequestMapping(value="/picture", method=RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadProfilePicture(file);
		return ResponseEntity.created(uri).build();
	}
}
