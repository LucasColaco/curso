package com.lucascolaco.curso.services; // service consulta o repositorio



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascolaco.curso.domain.Pedido;
import com.lucascolaco.curso.repositories.PedidoRepository;
import com.lucascolaco.curso.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private PedidoRepository repo; // dependencia de obj
	
	public Pedido buscar(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}

}
