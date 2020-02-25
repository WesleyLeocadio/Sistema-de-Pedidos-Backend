package com.wesley.cursomc.services;

import com.wesley.cursomc.resources.CategoriaResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesley.cursomc.domain.Categoria;
import com.wesley.cursomc.repositories.CategoriaRepository;
import com.wesley.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Integer id) {
		Categoria obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: "+id
					+", Tipo: "+Categoria.class.getName());
		}
		return obj;
	}

	public Categoria insert(Categoria obj){
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
