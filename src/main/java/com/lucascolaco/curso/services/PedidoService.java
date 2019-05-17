package com.lucascolaco.curso.services; // service consulta o repositorio



import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucascolaco.curso.domain.ItemPedido;
import com.lucascolaco.curso.domain.PagamentoComBoleto;
import com.lucascolaco.curso.domain.Pedido;
import com.lucascolaco.curso.domain.enums.EstadoPagamento;
import com.lucascolaco.curso.repositories.ItemPedidoRepository;
import com.lucascolaco.curso.repositories.PagamentoRepository;
import com.lucascolaco.curso.repositories.PedidoRepository;
import com.lucascolaco.curso.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private PedidoRepository repo; // dependencia de obj
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	@Transactional
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
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
		
		
	}

}
