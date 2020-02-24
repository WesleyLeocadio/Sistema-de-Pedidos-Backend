package com.wesley.cursomc.services;

import com.wesley.cursomc.domain.Categoria;
import com.wesley.cursomc.domain.Pedido;
import com.wesley.cursomc.repositories.CategoriaRepository;
import com.wesley.cursomc.repositories.PedidoRepository;
import com.wesley.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;

	public Pedido buscar(Integer id) {
		Pedido obj = repo.findOne(id);

		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: "+id
					+", Tipo: "+Categoria.class.getName());
		}
		return obj;
	}
	

}
