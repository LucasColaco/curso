package com.lucascolaco.curso.services; // service consulta o repositorio



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucascolaco.curso.domain.Categoria;
import com.lucascolaco.curso.repositories.CategoriaRepository;
import com.lucascolaco.curso.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private CategoriaRepository repo; // dependencia de obj
	
	public Categoria buscar(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); // tem que ser nulo, pois se tiver algum id, o metodo save vai pensar que é um atualizacao.
		return repo.save(obj);
		
	}

}
