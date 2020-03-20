package com.wesley.cursomc.services;

import com.wesley.cursomc.resources.CategoriaResource;
import com.wesley.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.wesley.cursomc.domain.Categoria;
import com.wesley.cursomc.repositories.CategoriaRepository;
import com.wesley.cursomc.services.exceptions.ObjectNotFoundException;

import java.util.List;

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

	public List<Categoria> findAll() {
		return repo.findAll();
	}


	public Categoria insert(Categoria obj){
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.delete(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
	}
}
