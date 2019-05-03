package com.lucascolaco.curso.services;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascolaco.curso.domain.Categoria;
import com.lucascolaco.curso.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private CategoriaRepository repo; // dependencia de obj
	
	public Categoria buscar(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		
	}

}
