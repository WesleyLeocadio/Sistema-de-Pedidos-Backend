package com.wesley.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wesley.cursomc.domain.Cliente;
import com.wesley.cursomc.repositories.ClienteRepository;
import com.wesley.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public Cliente find(Integer id) {
		Cliente obj = repo.findOne(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: "+id
					+", Tipo: "+Cliente.class.getName());
		}
		return obj;
	}
	

}
