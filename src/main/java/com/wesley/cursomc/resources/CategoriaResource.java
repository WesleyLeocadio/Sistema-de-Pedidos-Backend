package com.wesley.cursomc.resources;


import com.wesley.cursomc.dto.CategoriaDTO;
import com.wesley.cursomc.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wesley.cursomc.domain.Categoria;
import com.wesley.cursomc.services.CategoriaService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	@RequestMapping( value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>>findAll() {
		List<Categoria> list= service.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	//ResponseEntity: Vai ser uma resposta http --- RequestBody converte objeto json em java
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
		obj = service.insert(obj);
		//Pega a uri do novo recurso inserido
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		//returna o código 201 e a uri
		return ResponseEntity.created(uri).build();
	}


	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = service.update(obj);
		//returna conteudo vazio
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
