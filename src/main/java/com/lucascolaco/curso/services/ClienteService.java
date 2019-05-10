package com.lucascolaco.curso.services; // service consulta o repositorio



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascolaco.curso.domain.Cliente;
import com.lucascolaco.curso.repositories.ClienteRepository;
import com.lucascolaco.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private ClienteRepository repo; // dependencia de obj
	
	public Cliente find(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}

}
