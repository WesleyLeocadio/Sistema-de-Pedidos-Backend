package com.wesley.cursomc.services;

import com.wesley.cursomc.domain.Categoria;
import com.wesley.cursomc.domain.ItemPedido;
import com.wesley.cursomc.domain.PagamentoComBoleto;
import com.wesley.cursomc.domain.Pedido;
import com.wesley.cursomc.domain.enums.EstadoPagamento;
import com.wesley.cursomc.repositories.*;
import com.wesley.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;


import java.util.Optional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repo;
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Pedido obj = repo.findOne(id);

		if(obj == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! id: "+id
					+", Tipo: "+Categoria.class.getName());
		}
		return obj;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		return obj;
	}

}
