package com.lucascolaco.curso.services; // service consulta o repositorio



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.lucascolaco.curso.domain.Categoria;
import com.lucascolaco.curso.domain.Produto;
import com.lucascolaco.curso.repositories.CategoriaRepository;
import com.lucascolaco.curso.repositories.ProdutoRepository;
import com.lucascolaco.curso.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired // instanciacao do obj, vai ser automaticamente instanciada
	private ProdutoRepository repo; // dependencia de obj
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) { // busca no banco de dados e retorna pronto.
		Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids); 
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		
		
		
	}

}
